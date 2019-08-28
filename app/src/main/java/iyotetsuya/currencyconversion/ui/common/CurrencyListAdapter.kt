//package iyotetsuya.currencyconversion.ui.common
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.databinding.DataBindingComponent
//import androidx.databinding.DataBindingUtil
//import androidx.recyclerview.widget.DiffUtil
//import iyotetsuya.currencyconversion.AppExecutors
//import iyotetsuya.currencyconversion.R
//import iyotetsuya.currencyconversion.databinding.CurrencyItemBinding
//import iyotetsuya.currencyconversion.vo.Currency
//
//class CurrencyListAdapter(
//        private val dataBindingComponent: DataBindingComponent,
//        appExecutors: AppExecutors,
//        private val showFullName: Boolean,
//        private val repoClickCallback: ((Currency) -> Unit)?
//) : DataBoundListAdapter<Currency, CurrencyItemBinding>(
//        appExecutors = appExecutors,
//        diffCallback = object : DiffUtil.ItemCallback<Currency>() {
//            override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
//                return oldItem.code == newItem.code
//                        && oldItem.name == newItem.name
//            }
//
//            override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
//                return true
//            }
//        }
//) {
//    override fun createBinding(parent: ViewGroup): CurrencyItemBinding {
//        val binding = DataBindingUtil.inflate<CurrencyItemBinding>(
//                LayoutInflater.from(parent.context),
//                R.layout.currency_item,
//                parent,
//                false,
//                dataBindingComponent
//        )
////        binding.showFullName = showFullName
//        binding.root.setOnClickListener {
//            //            binding.repo?.let {
////                repoClickCallback?.invoke(it)
////            }
//        }
//        return binding
//    }
//
//    override fun bind(binding: CurrencyItemBinding, item: Currency) {
////        binding.currency = item
//    }
//
//    //    override fun createBinding(parent: ViewGroup): CurrencyItemBinding {
//
////    }
////
////    override fun bind(binding: CurrencyItemBinding, item: Currency) {
//////
////    }
//}