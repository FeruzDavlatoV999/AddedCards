package com.maverick.addedcards.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maverick.addedcards.R
import com.maverick.addedcards.databinding.FragmentAddCardBinding
import com.maverick.addedcards.databinding.FragmentCardBinding
import com.maverick.addedcards.model.Card
import com.maverick.addedcards.util.UiState
import com.maverick.addedcards.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCardFragment : Fragment() {
    private val binding by lazy { FragmentAddCardBinding.inflate(layoutInflater)}
    val viewModel: CardViewModel by viewModels()
    var objData: Card? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        binding.btnContinue.setOnClickListener {
            if (validation()){
                viewModel.addCard(getCard())
                findNavController().navigate(R.id.cardFragment)
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.cardFragment)
        }
    }

    private fun observer() {
        viewModel.addCard.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    binding.progress.visibility = View.VISIBLE

                }
                is UiState.Failure -> {
                    binding.progress.visibility = View.GONE
                    toast(state.error)
                    Log.d("AddCardFragment", "observer: ${state.error}")

                }
                is UiState.Success -> {
                    binding.progress.visibility = View.GONE
                    toast(state.data.second)
                    objData = state.data.first

                }
            }
        }
    }
    private fun getCard(): Card {
        return Card(
            cardHolder = "John",
            cardNumber= binding.etCardNumber.text.toString(),
            expireDate = binding.etValidityPeriodOfTheCard.text.toString(),
            money = "00.0 UZS"
        )
    }

    private fun validation(): Boolean {
        var isValid = true
        if (binding.etCardNumber.text.toString().isNullOrEmpty()) {
            isValid = false
            toast("CardNumber missing")
        }
        if (binding.etValidityPeriodOfTheCard.text.toString().isNullOrEmpty()) {
            isValid = false
            toast("Expire Date missing")
        }
        return isValid
    }
}