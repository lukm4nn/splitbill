package com.qti.splitbill.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qti.splitbill.databinding.ItemLayoutFoodsBinding
import com.qti.splitbill.entity.Foods
import com.qti.splitbill.entity.MemberSplitBill
import com.qti.splitbill.helper.Utils

class AdapterFoods (
    private val context: Context,
    private val item_list: List<Foods>,
) : RecyclerView.Adapter<AdapterFoods.HolderFoods>() {

    inner class HolderFoods(val binding: ItemLayoutFoodsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderFoods {
        val binding = ItemLayoutFoodsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderFoods(binding)
    }

    override fun getItemCount(): Int {
        return item_list.size
    }

    override fun onBindViewHolder(holder: HolderFoods, position: Int) {
        var item: Foods = item_list.get(position)

        if (item.isFood){
            holder.binding.tvLabel.text = "Makanan"
        }else{
            holder.binding.tvLabel.text = "Minuman"
        }
        holder.binding.tvFood.setText("${item.qty} ${item.nama_foods}")
        holder.binding.tvHargaSatuan.text = "@ ${Utils.getPriceFormat(item.price_foods)}"
        val qtyDouble = item.qty.toDouble()
        val sumprice = qtyDouble * item.price_foods
        holder.binding.tvPriceFood.text = Utils.getPriceFormat(sumprice)

    }

}