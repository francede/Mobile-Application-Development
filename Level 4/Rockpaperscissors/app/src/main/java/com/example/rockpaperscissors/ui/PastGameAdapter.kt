package com.example.rockpaperscissors.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.model.Choice
import com.example.rockpaperscissors.model.PastGame
import com.example.rockpaperscissors.model.Winner
import kotlinx.android.synthetic.main.history_item.view.*
import java.util.*

class PastGameAdapter(private val pastGames:List<PastGame>, private val context:Context): RecyclerView.Adapter<PastGameAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pastGame: PastGame) {
            itemView.tvTimestamp.text = Date(pastGame.epoch).toString()
            itemView.tvWinner.text = when(pastGame.winner){
                Winner.PLAYER -> context.getString(R.string.win)
                Winner.DRAW -> context.getString(R.string.draw)
                Winner.COMPUTER -> context.getString(R.string.lose)
            }
            itemView.ivYou.setImageResource(
                when(pastGame.playerChoice){
                    Choice.ROCK -> R.drawable.rock
                    Choice.PAPER -> R.drawable.paper
                    Choice.SCISSORS -> R.drawable.scissors
                }
            )
            itemView.ivCom.setImageResource(
                when(pastGame.computerChoice){
                    Choice.ROCK -> R.drawable.rock
                    Choice.PAPER -> R.drawable.paper
                    Choice.SCISSORS -> R.drawable.scissors
                }
            )
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false))
    }

    override fun getItemCount(): Int {
        return pastGames.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pastGames[position])
    }
}