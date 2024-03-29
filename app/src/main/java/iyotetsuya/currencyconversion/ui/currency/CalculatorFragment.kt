package iyotetsuya.currencyconversion.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import iyotetsuya.currencyconversion.databinding.CalculatorFragmentBinding
import iyotetsuya.currencyconversion.di.Injectable
import iyotetsuya.currencyconversion.util.autoCleared
import javax.inject.Inject

class CalculatorFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CalculatorViewModel by viewModels {
        viewModelFactory
    }

    private var binding by autoCleared<CalculatorFragmentBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = CalculatorFragmentBinding.inflate(inflater, container, false)
        dataBinding.currencyRateLoading.callback = object :
            RetryCallback {
            override fun retry() {
                viewModel.retry()
            }
        }
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner
        binding = dataBinding
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.init()
        setSpinnerData()
        setResult()
    }

    private fun setSpinnerData() {
        viewModel.supportedCurrencies.observe(viewLifecycleOwner) { resource ->
            resource.data?.let {
                val adapter = ArrayAdapter(
                    this.context!!,
                    android.R.layout.simple_spinner_item,
                    it
                )
                binding.currencySpinner.adapter = adapter
            }
        }
    }

    private fun setResult() {
        val adapter = ExchangeResultAdapter()
        binding.currencyRateList.adapter = adapter
        viewModel.exchangeResult().observe(viewLifecycleOwner) { list ->
            list?.let {
                adapter.submitList(it)
            }
        }
    }

}