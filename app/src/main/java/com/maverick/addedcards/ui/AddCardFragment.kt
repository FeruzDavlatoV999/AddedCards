package com.maverick.addedcards.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
        binding.addBtn.setOnClickListener {
            viewModel.addCard(getCard())
            toast("Clicked Button")
        }
    }

    private fun observer() {
        viewModel.addCard.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {

                }
                is UiState.Failure -> {
                    toast(state.error)
                    Log.d("AddCardFragment", "observer: ${state.error}")
                }
                is UiState.Success -> {
                    toast(state.data.second)
                    objData = state.data.first
                }
            }
        }
    }
    private fun getCard():Card{
        return Card(
            cardHolder = "John",
            cardNumber= "8600000000000000",
            expireDate = "1225",
            money = "0000"
        )
    }
}