package  iyotetsuya.currencyconversion.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import iyotetsuya.currencyconversion.databinding.CalculatorItemBinding
import iyotetsuya.currencyconversion.ui.currency.CalculatorViewModel.ExchangeResult

class ExchangeResultAdapter :
    ListAdapter<ExchangeResult, ExchangeResultAdapter.ViewHolder>(ExangeResultDiffCallback()) {

    var onItemClick: ((ExchangeResult) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CalculatorItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exchangeResult = getItem(position)
        holder.apply {
            bind(exchangeResult)
            itemView.tag = exchangeResult
        }
    }


    inner class ViewHolder(
        private val binding: CalculatorItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }

        fun bind(item: ExchangeResult) {
            binding.exchangeResult = item
        }
    }
}

private class ExangeResultDiffCallback : DiffUtil.ItemCallback<ExchangeResult>() {
    override fun areItemsTheSame(oldItem: ExchangeResult, newItem: ExchangeResult): Boolean {
        return oldItem.currencyCode == newItem.currencyCode
    }

    override fun areContentsTheSame(oldItem: ExchangeResult, newItem: ExchangeResult): Boolean {
        return oldItem.value == newItem.value
    }
}