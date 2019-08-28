package iyotetsuya.currencyconversion.ui.currency


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import iyotetsuya.currencyconversion.databinding.CalculatorFragmentBinding
import iyotetsuya.currencyconversion.di.Injectable
import iyotetsuya.currencyconversion.ui.common.CurrencyRateAdapter
import iyotetsuya.currencyconversion.util.autoCleared
import iyotetsuya.currencyconversion.vo.Currency
import javax.inject.Inject

class CalculatorFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CalculatorViewModel by viewModels {
        viewModelFactory
    }

    var binding by autoCleared<CalculatorFragmentBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = CalculatorFragmentBinding.inflate(inflater, container, false)
        binding = dataBinding
        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setSpinnerData()
        setResult()
    }

    private fun setSpinnerData() {
        viewModel.supportedCurrencies.observe(viewLifecycleOwner) { currency ->
            currency.data?.let {
                val adapter = ArrayAdapter<Currency>(
                    this.context!!,
                    android.R.layout.simple_spinner_item,
                    it
                )
                binding.currencySpinner.adapter = adapter
            }
        }
    }

    private fun setResult() {
        val adapter = CurrencyRateAdapter()
        binding.quoteList.adapter = adapter
        viewModel.quotes.observe(viewLifecycleOwner) { quotes ->
            adapter.submitList(quotes.data)
        }

    }


}