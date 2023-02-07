package com.maverick.addedcards.repository

import com.maverick.addedcards.model.Card
import com.maverick.addedcards.util.UiState

interface CardRepository {
    fun getCards(result: (UiState<List<Card>>) -> Unit)
    fun addCard(card: Card, result: (UiState<Pair<Card,String>>) -> Unit)
}