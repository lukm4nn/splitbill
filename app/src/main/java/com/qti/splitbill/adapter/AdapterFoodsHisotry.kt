package com.qti.splitbill.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qti.splitbill.databinding.ItemLayoutFoodsBinding
import com.qti.splitbill.databinding.ItemListMenuBinding
import com.qti.splitbill.entity.Foods
import com.qti.splitbill.entity.MemberSplitBill
import com.qti.splitbill.helper.Utils

class AdapterFoodsHisotry (
    private val context: Context,
    private val item_list: List<Foods>,
) : RecyclerView.Adapter<AdapterFoodsHisotry.HolderFoods>() {

    inner class HolderFoods(val binding: ItemListMenuBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderFoods {
        val binding = ItemListMenuBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderFoods(binding)
    }

    override fun getItemCount(): Int {
        return item_list.size
    }

    override fun onBindViewHolder(holder: HolderFoods, position: Int) {
        var item:Foods = item_list[position]
        holder.binding.tvTitleMenu.text = item.nama_foods
        holder.binding.tvPriceMenu.text = Utils.getPriceFormat(item.price_foods)
    }

}