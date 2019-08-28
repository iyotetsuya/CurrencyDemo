package iyotetsuya.currencyconversion.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import iyotetsuya.currencyconversion.databinding.InputFragmentBinding
import iyotetsuya.currencyconversion.di.Injectable
import iyotetsuya.currencyconversion.ui.common.QuoteAdapter
import iyotetsuya.currencyconversion.util.autoCleared
import javax.inject.Inject

class InputFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: InputViewModel by viewModels {
        viewModelFactory
    }

    var binding by autoCleared<InputFragmentBinding>()
    val args: InputFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = InputFragmentBinding.inflate(inflater, container, false)
        val adapter = QuoteAdapter()
        dataBinding.quoteList.adapter = adapter
        subscribeUi(adapter)
        binding = dataBinding
        binding.currencyCode = args.currencyCode
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setCurrency(args.currencyCode)
        binding.model = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }


    private fun subscribeUi(adapter: QuoteAdapter) {
        viewModel.quotes.observe(viewLifecycleOwner) { quotes ->
            adapter.submitList(quotes.data)
        }
    }

}
