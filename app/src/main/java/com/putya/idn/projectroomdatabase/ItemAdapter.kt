package com.putya.idn.projectroomdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.putya.idn.projectroomdatabase.databinding.ItemRowBinding

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private lateinit var binding: ItemRowBinding
    private var item = emptyList<Item>()

    internal fun setItem(items: List<Item>) {
        this.item = items
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Item) {
            with(binding) {
                tvItem.text = word.item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_row,
            parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        holder.bind(item[position])
    }
}