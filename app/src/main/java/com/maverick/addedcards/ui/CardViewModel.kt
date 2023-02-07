package com.maverick.addedcards.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maverick.addedcards.model.Card
import com.maverick.addedcards.repository.CardRepository
import com.maverick.addedcards.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    val repository: CardRepository
):ViewModel() {

    private val _cards = MutableLiveData<UiState<List<Card>>>()
    val note: LiveData<UiState<List<Card>>>
        get() = _cards

    private val _addCard= MutableLiveData<UiState<Pair<Card,String>>>()
    val addCard: LiveData<UiState<Pair<Card,String>>>
        get() = _addCard

    fun getCards() {
        _cards.value = UiState.Loading
        repository.getCards{ _cards.value = it }
    }

    fun addCard(card: Card){
        _addCard.value = UiState.Loading
        repository.addCard(card) { _addCard.value = it }
    }

}