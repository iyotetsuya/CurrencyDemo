package iyotetsuya.currencyconversion.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import iyotetsuya.currencyconversion.databinding.CurrencyListFragmentBinding
import iyotetsuya.currencyconversion.di.Injectable
import iyotetsuya.currencyconversion.ui.common.CurrencyAdapter
import javax.inject.Inject

class CurrencyListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CurrencyListViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = CurrencyListFragmentBinding.inflate(inflater, container, false)
        val adapter = CurrencyAdapter()
        binding.currencyList.adapter = adapter
        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: CurrencyAdapter) {
        viewModel.currencies.observe(viewLifecycleOwner) { currencies ->
            adapter.submitList(currencies.data)
        }
    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
////
////        viewModel = ViewModelProviders.of(this, viewModelFactory)
////            .get(CurrencyListViewModel::class.java)
////
//        val currencies = viewModel.currencies
//        currencies.observe(this, Observer { list ->
//            if (list?.data != null) {
////                adapter.submitList(list.data)
//            } else {
////                adapter.submitList(emptyList())
//            }
//        })
//////        val adapter = CurrencyAdapter(dataBindingComponent, appExecutors) { contributor ->
//////            navController().navigate(
//////                    RepoFragmentDirections.showUser(contributor.login)
//////            )
//////        }
////
////        this.adapter = adapter
////
//    }
////
////
////    fun navController() = findNavController()
}