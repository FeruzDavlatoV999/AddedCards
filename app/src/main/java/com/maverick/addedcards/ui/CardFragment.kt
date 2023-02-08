package com.maverick.addedcards.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maverick.addedcards.R
import com.maverick.addedcards.adapter.CardAdapter
import com.maverick.addedcards.databinding.FragmentCardBinding
import com.maverick.addedcards.util.UiState
import com.maverick.addedcards.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFragment : Fragment() {

    private val binding by lazy { FragmentCardBinding.inflate(layoutInflater)}
    val viewModel: CardViewModel by viewModels()
    private val cardAdapter by lazy { CardAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.rvCards.adapter = cardAdapter
        oberver()
        viewModel.getCards()

        binding.ivAdd.setOnClickListener { findNavController().navigate(R.id.addCardFragment) }
    }
    private fun oberver(){
        viewModel.note.observe(viewLifecycleOwner) { state ->
            when(state){
                is UiState.Loading -> {
                    Log.d("CardFragment", "oberver: loading")
                    binding.progress.visibility = View.VISIBLE

                }
                is UiState.Failure -> {
                    binding.progress.visibility = View.GONE
                    toast(state.error)
                    Log.d("CardFragment", "oberver: ${state.error}")

                }
                is UiState.Success -> {
                    binding.progress.visibility = View.GONE
                    Log.d("CardFragment", "oberver: ${state.data}")
                    cardAdapter.submitData(state.data)
                }
            }
        }
    }

    private fun initViews() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.str_all)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.str_sevimli)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.str_uzcard)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.str_humo)))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(resources.getString(R.string.str_international_cards)))
    }

}