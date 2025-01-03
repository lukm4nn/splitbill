package com.qti.splitbill.helper

import android.graphics.Bitmap
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.qti.splitbill.entity.ParsedItem

class TextRecognitionHandler {

    fun processImageWithMLKit(bitmap: Bitmap, callback: (List<String>) -> Unit) {
        val image = InputImage.fromBitmap(bitmap, 0)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                val result = processTextByRows(visionText)
                callback(result)
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
                callback(emptyList())
            }
    }

    private fun processTextByRows(result: Text): List<String> {
        val lines = result.textBlocks.flatMap { it.lines }
        val dynamicThreshold = calculateDynamicThreshold(lines)
        val rows = groupByRows(lines, dynamicThreshold)

        return rows.map { mergeLinesByRow(it) }
    }

    private fun calculateDynamicThreshold(lines: List<Text.Line>): Int {
        val heights = lines.mapNotNull { it.boundingBox?.height() }.filter { it > 0 }
        return if (heights.isNotEmpty()) {
            ((heights.average() * 0.5) + 10).toInt()
        } else {
            30 // Default threshold
        }
    }

    private fun groupByRows(lines: List<Text.Line>, threshold: Int): List<List<Text.Line>> {
        val rows = mutableListOf<MutableList<Text.Line>>()

        for (line in lines) {
            val boundingBox = line.boundingBox ?: continue
            var addedToRow = false

            for (row in rows) {
                val firstBoundingBox = row.first().boundingBox ?: continue
                if (Math.abs(boundingBox.top - firstBoundingBox.top) < threshold) {
                    row.add(line)
                    addedToRow = true
                    break
                }
            }

            if (!addedToRow) {
                rows.add(mutableListOf(line))
            }
        }
        return rows
    }

    private fun mergeLinesByRow(row: List<Text.Line>): String {
        return row.sortedBy { it.boundingBox?.left ?: 0 }
            .joinToString(" ") { it.text }
    }
}
