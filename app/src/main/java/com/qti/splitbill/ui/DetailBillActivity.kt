package com.qti.splitbill.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.qti.splitbill.adapter.AdapterMemberSplitBill
import com.qti.splitbill.databinding.ActivityDetailBillBinding
import com.qti.splitbill.entity.MemberSplitBill
import com.qti.splitbill.helper.Utils

class DetailBillActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBillBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterMemberSplitBill: AdapterMemberSplitBill
    private lateinit var item_member: ArrayList<MemberSplitBill>
    private lateinit var gson: Gson
    private var isPajak: Boolean = false
    private var isDiscount: Boolean = false
    private var isOngkir: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBillBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initview()
        initEvent()

    }

    private fun initEvent() {
        binding.btAddMemberSplit.setOnClickListener {
            val intent = Intent(this, AddBillActivity::class.java)
            startActivityForResult(intent, Utils.START_ACTIVITY_RESULT)
        }
        binding.cbDiscount.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.lytDiscount.visibility = View.VISIBLE
                isDiscount = true
            }else{
                binding.lytDiscount.visibility = View.GONE
                isDiscount = false
            }
        }

        binding.cbPajak.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.lytPajak.visibility = View.VISIBLE
                isPajak = true
            }else{
                binding.lytPajak.visibility = View.GONE
                isPajak = false
            }
        }

        binding.cbOngkir.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.lytOngkir.visibility = View.VISIBLE
                isOngkir = true
            }else{
                binding.lytOngkir.visibility = View.GONE
                isOngkir = false
            }
        }

        binding.cbDiscountOngkir.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.lytDiscOngkir.visibility = View.VISIBLE
            }else{
                binding.lytDiscOngkir.visibility = View.GONE
            }
        }

        binding.btHitung.setOnClickListener {
            if (isDiscount && isOngkir && isPajak){
                calculateWithDiscountOngkirPajak()
            }else if (isDiscount && isOngkir){
                calculateWithOngkirDiscount()
            }else if (isPajak && isOngkir){
                calculateWithPajakOngkir()
            }else if (isPajak && isDiscount){
                calculateWithDiscountPajak()
            }else if (isDiscount){
                calculateWithDiscount()
            }else if (isOngkir){
                calculateWithOngkir()
            }else if (isPajak){
                calculateWithPajak()
            }else{
                calculateNormal()
            }
        }
    }

    private fun initview() {

        item_member = ArrayList()
        gson = Gson()

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvMemberSplitBill.layoutManager = linearLayoutManager
        adapterMemberSplitBill = AdapterMemberSplitBill(this, item_member)
        binding.rvMemberSplitBill.adapter = adapterMemberSplitBill

        binding.etDiscount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("char", s.toString())
            }
        })

        binding.etPajak.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                
            }

        })

    }

    private fun calculateWithDiscountOngkirPajak(){

    }

    private fun calculateWithDiscountPajak(){

    }

    private fun calculateWithPajakOngkir(){

    }

    private fun calculateWithOngkirDiscount(){

    }

    private fun calculateWithPajak(){

    }

    private fun calculateWithDiscount(){

    }

    private fun calculateWithOngkir(){

    }

    private fun calculateNormal(){

    }

    private fun isValidate(): Boolean{
        if (TextUtils.isEmpty(binding.etTitleSplit.text.toString())){
            Utils.showToast(this, "Nama Resto tidak boleh kosong",4)
            return false
        }
        if (TextUtils.isEmpty(binding.etTotalSplit.text.toString())){
            Utils.showToast(this, "Total Split Bill harap di isi",4)
            return false
        }

        if (item_member.size < 2){
            Utils.showToast(this, "Total Split Bill harap di isi",4)
            return false
        }
        if (binding.cbDiscount.isChecked && TextUtils.isEmpty(binding.etDiscount.text.toString())){
            Utils.showToast(this, "total discount harap di isi",4)
        }
        if (binding.cbOngkir.isChecked && TextUtils.isEmpty(binding.etOngkir.text.toString())){
            Utils.showToast(this, "total ONGKIR harap di isi",4)
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Utils.START_ACTIVITY_RESULT){
            if (resultCode == Activity.RESULT_OK){
                val datagson = data!!.getStringExtra("gsonMember")
                val stringtoJson = gson.fromJson(datagson,MemberSplitBill::class.java)
                item_member.add(stringtoJson)
                adapterMemberSplitBill.notifyDataSetChanged()
            }
        }
    }
}