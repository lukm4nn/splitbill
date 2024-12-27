package com.qti.splitbill.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qti.splitbill.R
import com.qti.splitbill.databinding.ItemBillBinding
import com.qti.splitbill.databinding.ItemLayoutBillsBinding
import com.qti.splitbill.entity.MemberSplitBill
import com.qti.splitbill.entity.SplitBill
import com.qti.splitbill.helper.Utils

class AdapterMemberSplitBill (
    private val context: Context,
    private val item_list: List<MemberSplitBill>,
) : RecyclerView.Adapter<AdapterMemberSplitBill.HolderMemberSplitBill>() {

    inner class HolderMemberSplitBill(val binding: ItemLayoutBillsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderMemberSplitBill {
        val binding = ItemLayoutBillsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderMemberSplitBill(binding)
    }

    override fun getItemCount(): Int {
        return item_list.size
    }

    override fun onBindViewHolder(holder: HolderMemberSplitBill, position: Int) {
        val item = item_list[position]
        holder.binding.tvOrderName.text = item.nama
        if (item.isDiscount && item.isOngkir && item.isPajak){
            holder.binding.tvStateDiscount.text = context.resources.getString(R.string.label_ongkir_pajak_discount)
            holder.binding.tvStateDiscount.visibility = View.VISIBLE
        }else if (item.isDiscount && item.isPajak){
            holder.binding.tvStateDiscount.text = context.resources.getString(R.string.label_pajak_discount)
            holder.binding.tvStateDiscount.visibility = View.VISIBLE
        }else{
            holder.binding.tvStateDiscount.visibility = View.GONE
        }
        holder.binding.tvCountOrder.text = item.list_foods.size.toString()
        holder.binding.tvTotalOrder.text = Utils.getPriceFormat(item.total_belanja)
    }
}