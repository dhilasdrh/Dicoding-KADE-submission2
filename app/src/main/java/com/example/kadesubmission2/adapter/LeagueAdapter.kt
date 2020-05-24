package com.example.kadesubmission2.adapter


import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadesubmission2.LeagueList
import com.example.kadesubmission2.R
import com.example.kadesubmission2.model.League
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext

class LeagueAdapter(private var leagues: List<League>, private var listener: (League) -> Unit)
    : RecyclerView.Adapter<LeagueAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LeagueList().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindItem(leagues[position], listener)
    }

    override fun getItemCount(): Int = leagues.size

    class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val leagueImg: ImageView = view.findViewById(R.id.id_img)
        private val leagueName: TextView = view.findViewById(R.id.id_name)

        fun bindItem(items: League, listener: (League) -> Unit) {
            items.image?.let { Picasso.get().load(it).fit().into(leagueImg) }
            leagueName.text = items.name
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}
