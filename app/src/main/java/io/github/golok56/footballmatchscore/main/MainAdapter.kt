package io.github.golok56.footballmatchscore.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.base.BaseViewHolder
import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.schedule.ScheduleActivity
import kotlinx.android.synthetic.main.item_league.view.*
import org.jetbrains.anko.startActivity

class MainAdapter(
        private var leagues: MutableList<League>
) : androidx.recyclerview.widget.RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater
            .from(parent.context).inflate(R.layout.item_league, parent, false))

    override fun getItemCount() = leagues.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bindItem(leagues[pos])
    }

    fun updateData(newLeagues: MutableList<League>?) {
        leagues.clear()
        leagues.add(0, League(FAVS, "Favorites", "Soccer", "", ""))
        newLeagues?.let { leagues.addAll(it) }
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : BaseViewHolder<League>(view) {
        override fun bindItem(item: League) {
            itemView.tvLeagueName.text = item.name
            itemView.setOnClickListener {
                it.context.startActivity<ScheduleActivity>(
                        ScheduleActivity.EXTRA_LEAGUE_ID to item.id)
            }

            if (item.id == FAVS) {
                Glide.with(itemView.context)
                    .load(R.drawable.ic_star_red_24dp)
                    .into(itemView.ivLeagueLogo)
            } else {
                Glide.with(itemView.context)
                    .load(item.logo)
                    .into(itemView.ivLeagueLogo)
            }
        }
    }

    companion object {
        const val FAVS = "favs"
    }
}