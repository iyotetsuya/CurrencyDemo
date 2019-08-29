//package  iyotetsuya.currencyconversion.ui.common
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import iyotetsuya.currencyconversion.databinding.CurrencyItemBinding
//import iyotetsuya.currencyconversion.vo.SupportedCurrency
//
//class CurrencyAdapter : ListAdapter<SupportedCurrency, CurrencyAdapter.ViewHolder>(CurrencyDiffCallback()) {
//
////    var onItemClick: ((SupportedCurrency) -> Unit)? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            CurrencyItemBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val currency = getItem(position)
//        holder.apply {
//            bind(currency)
//            itemView.tag = currency
//        }
//    }
//
//
//    inner class ViewHolder(
//        private val binding: CurrencyItemBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        init {
//            itemView.setOnClickListener {
//                onItemClick?.invoke(getItem(adapterPosition))
//            }
//        }
//
//        fun bind(item: SupportedCurrency) {
//            binding.currency = item
//        }
//    }
//}
//
//private class CurrencyDiffCallback : DiffUtil.ItemCallback<SupportedCurrency>() {
//    override fun areItemsTheSame(oldItem: SupportedCurrency, newItem: SupportedCurrency): Boolean {
//        return oldItem.code == newItem.code
//    }
//
//    override fun areContentsTheSame(oldItem: SupportedCurrency, newItem: SupportedCurrency): Boolean {
//        return oldItem.name == newItem.name
//    }
//}