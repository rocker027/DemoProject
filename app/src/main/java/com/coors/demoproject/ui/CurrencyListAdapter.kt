package com.coors.demoproject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coors.demoproject.data.currency.CurrencyInfo
import com.coors.demoproject.databinding.ItemCurrencyBinding
import java.util.*

typealias OnClickCurrencyItem = (item: CurrencyInfo) -> Unit

class CurrencyListAdapter(
    private val onClickCurrencyItem: OnClickCurrencyItem
) : ListAdapter<CurrencyInfo, CurrentListViewHolder>(object :
    DiffUtil.ItemCallback<CurrencyInfo?>() {
    override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo) =
        oldItem == newItem
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentListViewHolder {
        val viewBinding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrentListViewHolder(viewBinding).apply {
            // click listener
            this.itemView.setOnClickListener {
                onClickCurrencyItem(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: CurrentListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CurrentListViewHolder(private val viewBinding: ItemCurrencyBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(item: CurrencyInfo) {
        with(viewBinding) {
            tvMarker.text = item.id.take(1).uppercase(Locale.getDefault())
            tvName.text = item.name.orEmpty()
            tvSymbol.text = item.symbol.orEmpty()
        }
    }
}