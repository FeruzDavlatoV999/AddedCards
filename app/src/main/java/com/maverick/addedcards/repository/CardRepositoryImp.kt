package com.maverick.addedcards.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.maverick.addedcards.model.Card
import com.maverick.addedcards.util.UiState

class CardRepositoryImp(val database: FirebaseFirestore):CardRepository {
    override fun getCards(result: (UiState<List<Card>>) -> Unit) {
        database.collection("card")
            .get()
            .addOnSuccessListener {
                val cards = arrayListOf<Card>()
                for (document in it){
                    val card = document.toObject(Card::class.java)
                    cards.add(card)
                }
                result.invoke(
                    UiState.Success(cards)
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun addCard(card: Card, result: (UiState<Pair<Card, String>>) -> Unit) {
        val document = database.collection("card").document()

        document
            .set(card)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success(Pair(card,"Card has been created successfully"))
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }
}