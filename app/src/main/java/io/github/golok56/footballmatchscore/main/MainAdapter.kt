package io.github.golok56.footballmatchscore.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.schedule.ScheduleActivity
import org.jetbrains.anko.*

class MainAdapter(
    private var context: Context,
    private var leagues: MutableList<League>
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(MainAdapterUi(AnkoContext.create(context, parent)))

    override fun getItemCount() = leagues.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bindItem(leagues[pos])
    }

    fun updateData(newLeagues: MutableList<League>?) {
        leagues.clear()
        newLeagues?.let { leagues.addAll(it) }
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val ui: MainAdapterUi) : RecyclerView.ViewHolder(ui.view) {
        fun bindItem(league: League) {
            ui.leagueName.text = league.name
            ui.view.setOnClickListener {
                context.startActivity<ScheduleActivity>(ScheduleActivity.EXTRA_LEAGUE_ID to league.id)
            }
        }
    }
}