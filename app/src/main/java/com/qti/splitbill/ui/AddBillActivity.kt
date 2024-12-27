package com.qti.splitbill.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.qti.splitbill.R
import com.qti.splitbill.adapter.AdapterFoods
import com.qti.splitbill.databinding.ActivityAddBillBinding
import com.qti.splitbill.entity.Foods
import com.qti.splitbill.entity.MemberSplitBill
import com.qti.splitbill.helper.Utils

class AddBillActivity : AppCompatActivity() {

    private var _binding: ActivityAddBillBinding? = null
    private val binding get() = _binding!!

    private lateinit var item_foods: ArrayList<Foods>
    private lateinit var item_member: MemberSplitBill
    private lateinit var adapter: AdapterFoods
    private var state = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddBillBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
        initEvent()
        refreshFrame()
    }

    private fun initEvent() {
        binding.btAddFoods.setOnClickListener {
            if (isValidate()){
                addData()
            }
        }

        binding.rbMinuman.setOnClickListener {
            state = false
        }

        binding.rbMakanan.setOnClickListener {
            state = true
        }

        binding.btSimpan.setOnClickListener {
            Utils.showToast(this, "clicked",1)
            setResult()
        }

        binding.ivClose.setOnClickListener {
            onBackPressed()
        }
    }

    private fun addData() {
        val label_foods: String = binding.etLabelMakanan.text.toString()
        val hargaFoods: Double = binding.etHarga.text.toString().toDouble()
        val qtyFoods:Int = binding.etqty.text.toString().toInt()
        val itemFoods:Foods = Foods(label_foods, hargaFoods, state, qtyFoods)

        if (!handleduplicate(itemFoods)){
            item_foods.add(itemFoods)
        }
        refreshFrame()
    }

    private fun handleduplicate(itemFoods: Foods):Boolean {
        if (!item_foods.isEmpty()){
            for (i in 0..item_foods.size -1){
                if (item_foods[i].equals(itemFoods)){
                    Utils.showToast(this,"item sudah ada!!", 4)
                    return true
                }
            }
        }
        return false
    }

    private fun refreshFrame() {
        adapter.notifyDataSetChanged()
        if (item_foods.size < 1){
            binding.btSimpan.isEnabled = false
            binding.btSimpan.backgroundTintList = resources.getColorStateList(R.color.grey_500)
        }else{
            binding.btSimpan.isEnabled = true
            binding.btSimpan.backgroundTintList = resources.getColorStateList(R.color.blue_500)
        }
    }

    private fun isValidate(): Boolean {
        if (TextUtils.isEmpty(binding.etNamaMember.text.toString())) {
            Utils.showToast(this,"Tidak boleh kosong", 4)
            return false
        }
        if (TextUtils.isEmpty(binding.etLabelMakanan.getText())) {
            Utils.showToast(this,"label tidak boleh kosong", 4)
            return false
        }
        if (TextUtils.isEmpty(binding.etHarga.text.toString())) {
            Utils.showToast(this,"Harga tidak boleh kosong", 4)
            return false
        }
        if (TextUtils.isEmpty(binding.etqty.text.toString())){
            Utils.showToast(this,"QTY tidak boleh kosong", 4)
            return false
        }
        if (binding.rbMakanan.isChecked == false && binding.rbMinuman.isChecked == false){
            Utils.showToast(this,"Pilih makanan atau minuman", 4)
            return false
        }

        return true
    }

    private fun initView() {
        item_foods = ArrayList()
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvFoods.layoutManager = linearLayoutManager
        adapter = AdapterFoods(this, item_foods)
        binding.rvFoods.adapter = adapter
    }

    private fun setResult(){
        val memberFood: String = binding.etNamaMember.text.toString()
        val totalPriceWithQty = item_foods.sumOf { it.price_foods * it.qty }
        item_member = MemberSplitBill(memberFood, totalPriceWithQty,
            false, 0.0, false, 0.0,
            false, 0.0, false, 0.0, item_foods)
        val gson: Gson = Gson()
        val stringgson = gson.toJson(item_member)

        val result = Intent()
        result.putExtra("gsonMember",stringgson)
        setResult(RESULT_OK, result)
        finish()
    }
}