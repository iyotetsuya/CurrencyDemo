package iyotetsuya.currencyconversion.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import iyotetsuya.currencyconversion.databinding.CurrencyListFragmentBinding
import iyotetsuya.currencyconversion.di.Injectable
import iyotetsuya.currencyconversion.ui.common.CurrencyAdapter
import iyotetsuya.currencyconversion.ui.common.RetryCallback
import iyotetsuya.currencyconversion.util.autoCleared
import javax.inject.Inject

class CurrencyListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CurrencyListViewModel by viewModels {
        viewModelFactory
    }

    var binding by autoCleared<CurrencyListFragmentBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dataBinding = CurrencyListFragmentBinding.inflate(inflater, container, false)
        val adapter = CurrencyAdapter()
        adapter.onItemClick = { currency ->
            val action =
                CurrencyListFragmentDirections.actionCurrencyListFragmentToInputFragment(currency.code)
            findNavController().navigate(action)
        }
        dataBinding.currencyList.adapter = adapter
        subscribeUi(adapter)
        dataBinding.retryCallback = object : RetryCallback {
            override fun retry() {
                viewModel.retry()
            }
        }
        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
        binding.data = viewModel.currencies
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun subscribeUi(adapter: CurrencyAdapter) {
        viewModel.currencies.observe(viewLifecycleOwner) { currencies ->
            adapter.submitList(currencies.data)
        }
    }

}