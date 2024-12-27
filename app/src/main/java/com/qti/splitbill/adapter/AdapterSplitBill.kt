package com.qti.splitbill.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qti.splitbill.databinding.ItemBillBinding
import com.qti.splitbill.entity.SplitBill
import com.qti.splitbill.helper.Utils

class AdapterSplitBill(
    private val context: Context,
    private val item_list: List<SplitBill>,
) : RecyclerView.Adapter<AdapterSplitBill.HolderSplitBill>() {

    inner class HolderSplitBill(val binding: ItemBillBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderSplitBill {
        val binding = ItemBillBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderSplitBill(binding)
    }

    override fun getItemCount(): Int {
        return item_list.size
    }

    override fun onBindViewHolder(holder: HolderSplitBill, position: Int) {
        var item: SplitBill = item_list[position]

        holder.binding.tvTitleSplit.text = item.title_bill
        holder.binding.tvTotalSplit.text = Utils.getPriceFormat(item.total_bill)
        holder.binding.tvDateSplit.text = item.waktu
        holder.binding.tvCountOrder.text = "${item.listmember_split_bill.size} Orang"


    }

}