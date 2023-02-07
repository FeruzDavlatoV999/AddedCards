package com.maverick.addedcards.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Card(
    var cardHolder:String,
    var cardNumber:String,
    var expireDate:String,
    var money:String
){
    constructor(): this("", "","", "")
}
