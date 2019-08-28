package iyotetsuya.currencyconversion.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import iyotetsuya.currencyconversion.databinding.InputFragmentBinding
import iyotetsuya.currencyconversion.util.autoCleared

class InputFragment : Fragment() {

    val args: InputFragmentArgs by navArgs()

    var binding by autoCleared<InputFragmentBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = InputFragmentBinding.inflate(inflater, container, false)
        binding = dataBinding
        binding.currencyCode = args.currencyCode
        return binding.root
    }
}
