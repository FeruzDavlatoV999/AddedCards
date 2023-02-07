package com.maverick.addedcards.ui

import android.net.Uri
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
class CardFragment : Fragment() {

    private val binding by lazy { FragmentCardBinding.inflate(layoutInflater)}
    val viewModel: CardViewModel by viewModels()

    private var list: MutableList<Card> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        oberver()
        viewModel.getCards()
    }
    private fun oberver(){
        viewModel.note.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    Log.d("CardFragment", "oberver: loading")
                }
                is UiState.Failure -> {
                    toast(state.error)
                    Log.d("CardFragment", "oberver: ${state.error}")
                }
                is UiState.Success -> {
                    Log.d("CardFragment", "oberver: ${state.data}")
                }
            }
        }
    }

}