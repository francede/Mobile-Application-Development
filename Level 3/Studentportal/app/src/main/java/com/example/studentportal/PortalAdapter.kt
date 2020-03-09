package com.example.studentportal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.portal_item.view.*

class PortalAdapter(private val portals: List<Portal>, val clickListener: (Portal) -> Unit): RecyclerView.Adapter<PortalAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(portal: Portal){
            itemView.tvName.text = portal.name
            itemView.tvUri.text = portal.uri.toString()
            itemView.setOnClickListener{clickListener(portal)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.portal_item, parent, false))
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(portals[position])
    }

}