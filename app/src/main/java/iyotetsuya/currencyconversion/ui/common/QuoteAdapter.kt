package  iyotetsuya.currencyconversion.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import iyotetsuya.currencyconversion.databinding.QuoteItemBinding
import iyotetsuya.currencyconversion.vo.Quote

class QuoteAdapter : ListAdapter<Quote, QuoteAdapter.ViewHolder>(QuoteDiffCallback()) {

    var onItemClick: ((Quote) -> Unit)? = null

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

        fun bind(item: Quote) {
            binding.quote = item
        }
    }
}

private class QuoteDiffCallback : DiffUtil.ItemCallback<Quote>() {
    override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem.value == newItem.value
    }
}