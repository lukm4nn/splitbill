package com.qti.splitbill.helper

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.qti.splitbill.R
import es.dmoral.toasty.Toasty
import java.text.DecimalFormat

object Utils {

    val START_ACTIVITY_RESULT = 111
    val EDIT_START_ACTIVITY_RESULT = 112


    fun animasiClicked(context: Context): Animation{
        val animation = AnimationUtils.loadAnimation(context, R.anim.anim_scale_in)
        return animation
    }

    fun getPriceFormat(price: Double?): String? {
        val pattern = "###,###,###"
        val decimalFormat = DecimalFormat(pattern)
        val value = decimalFormat.format(price)
        return "Rp " + value.replace(",", ".")
    }

    fun getThousandFormat(`val`: Int?): String? {
        val pattern = "###,###,###"
        val decimalFormat = DecimalFormat(pattern)
        val value = decimalFormat.format(`val`)
        return value.replace(",", ".")
    }

    fun showToast(mContext: Context ,message: String, flag: Int) {
        if (flag == 0) {
            //error
            Toasty.error(mContext, message, Toast.LENGTH_SHORT).show()
        } else if (flag == 1) {
            //success
            Toasty.success(mContext, message, Toast.LENGTH_SHORT).show()
        } else if (flag == 2) {
            //info
            Toasty.info(mContext, message, Toast.LENGTH_SHORT).show()
        } else if (flag == 3) {
            //normal
            Toasty.normal(mContext, message, Toast.LENGTH_SHORT).show()
        } else if (flag == 4) {
            //warning
            Toasty.warning(mContext, message, Toast.LENGTH_SHORT).show()
        } else {
            Toasty.normal(mContext, message, Toast.LENGTH_SHORT).show()
        }
    }


}