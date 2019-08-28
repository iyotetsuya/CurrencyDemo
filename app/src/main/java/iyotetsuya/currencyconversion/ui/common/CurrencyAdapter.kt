package  iyotetsuya.currencyconversion.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import iyotetsuya.currencyconversion.databinding.CurrencyItemBinding
import iyotetsuya.currencyconversion.vo.Currency

class CurrencyAdapter : ListAdapter<Currency, CurrencyAdapter.ViewHolder>(CurrencyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CurrencyItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currency = getItem(position)
        holder.apply {
            bind(createOnClickListener(), currency)
            itemView.tag = currency
        }
    }

    private fun createOnClickListener(): View.OnClickListener {
        return View.OnClickListener {

        }
    }

    class ViewHolder(
        private val binding: CurrencyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: Currency) {
            binding.currency = item
        }
    }
}

//    private var items: List<Currency> = emptyList()
//
//    override fun getItemCount() = items.size
//
//    //
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            DataBindingUtil.inflate(
//                LayoutInflater.from(parent.context),
//                R.layout.currency_item, parent, false
//            )
//        )
//    }
//
//    //
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
////        holder.text.text = items[position].name
////        items[position].let { item ->
////            with(holder) {
////                itemView.tag = item
////                bind(item)
////            }
////        }
//    }
//
//    //
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
////        val text = itemView. as TextView
//    }
////}
////
private class CurrencyDiffCallback : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.name == newItem.name
    }
}