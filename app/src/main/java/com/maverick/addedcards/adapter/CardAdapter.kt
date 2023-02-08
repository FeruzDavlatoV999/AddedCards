package com.maverick.addedcards.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.maverick.addedcards.R
import com.maverick.addedcards.databinding.ItemCardBinding
import com.maverick.addedcards.model.Card

class CardAdapter:RecyclerView.Adapter<CardAdapter.VH>() {
    private val cards = ArrayList<Card>()




    inner class VH(val view: ItemCardBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(card: Card) {
            if(adapterPosition == 0) {
                view.tvMainCard.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.VH = VH(ItemCardBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CardAdapter.VH, position: Int) {
        val cards = cards[position]
        holder.view.apply {
            tvMainCard.text = holder.itemView.getContext().getString(R.string.main_card)
            tvCardNumber.text = cards.cardNumber
            tvUserName.text = cards.cardHolder
            tvExpireDate.text = cards.expireDate
            tvSalary.text = "00.0 UZS"
        }
        holder.bind(cards)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(list: List<Card>) {
        this.cards.clear()
        this.cards.addAll(list)
        notifyDataSetChanged()
    }
}