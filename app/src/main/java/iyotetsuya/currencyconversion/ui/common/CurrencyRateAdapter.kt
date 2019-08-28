package  iyotetsuya.currencyconversion.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import iyotetsuya.currencyconversion.databinding.QuoteItemBinding
import iyotetsuya.currencyconversion.vo.CurrencyRate

class CurrencyRateAdapter :
    ListAdapter<CurrencyRate, CurrencyRateAdapter.ViewHolder>(QuoteDiffCallback()) {

    var onItemClick: ((CurrencyRate) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            QuoteItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = getItem(position)
        holder.apply {
            bind(quote)
            itemView.tag = quote
        }
    }


    inner class ViewHolder(
        private val binding: QuoteItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }

        fun bind(item: CurrencyRate) {
            binding.currencyRate = item
        }
    }
}

private class QuoteDiffCallback : DiffUtil.ItemCallback<CurrencyRate>() {
    override fun areItemsTheSame(oldItem: CurrencyRate, newItem: CurrencyRate): Boolean {
        return oldItem.target == newItem.target && oldItem.source == newItem.source
    }

    override fun areContentsTheSame(oldItem: CurrencyRate, newItem: CurrencyRate): Boolean {
        return oldItem.rate == newItem.rate
    }
}