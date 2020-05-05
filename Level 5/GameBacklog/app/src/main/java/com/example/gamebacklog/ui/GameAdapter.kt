package com.example.gamebacklog.ui

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import kotlinx.android.synthetic.main.game_item.view.*
import java.util.*

class GameAdapter(private val reminders: List<Game>, private val context: Context) : RecyclerView.Adapter<GameAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false))
    }

    override fun getItemCount(): Int {
        return reminders.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reminders[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(game: Game) {
            itemView.tvTitle.text = game.name
            itemView.tvPlatform.text = game.platform
            val c = Calendar.getInstance()
            c.timeInMillis = game.releaseDate.time
            itemView.tvRelease.text = context.getString(
                R.string.release,
                c.get(Calendar.DAY_OF_MONTH),
                c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()),
                c.get(Calendar.YEAR))
        }
    }

}