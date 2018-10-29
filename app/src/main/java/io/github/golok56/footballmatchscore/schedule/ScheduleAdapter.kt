package io.github.golok56.footballmatchscore.schedule

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.github.golok56.footballmatchscore.base.BaseViewHolder
import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.scheduledetail.ScheduleDetailActivity
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.startActivity

class ScheduleAdapter(private val schedules: MutableList<Schedule>) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    private var date: String? = ""

    override fun getItemViewType(position: Int): Int {
        if (schedules[position].date == date && date?.isNotEmpty() == true) {
            return VIEW_TYPE_EVENT
        }

        date = schedules[position].date
        return VIEW_TYPE_DATE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        VIEW_TYPE_EVENT -> EventViewHolder(ScheduleAdapterEventUi(AnkoContext.create(parent.context, parent)))
        else -> DateViewHolder(ScheduleAdapterDateUi(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = schedules.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, pos: Int) {
        when (holder) {
            is EventViewHolder -> holder.bindItem(schedules[pos])
            is DateViewHolder -> holder.bindItem(date!!)
        }
    }

    fun updateData(data: MutableList<Schedule>) {
        schedules.clear()
        schedules.addAll(data)
        notifyDataSetChanged()
    }

    inner class EventViewHolder(private val ui: ScheduleAdapterEventUi) : BaseViewHolder<Schedule>(ui.view) {
        override fun bindItem(item: Schedule) {
            ui.homeTeam.text = item.homeTeam
            ui.awayTeam.text = item.awayTeam
            ui.score.text = "${item.homeScore ?: ""} vs ${item.awayScore ?: ""}"
            ui.view.setOnClickListener {
                itemView.context.startActivity<ScheduleDetailActivity>(ScheduleDetailActivity.EXTRA_SCHEDULE to item)
            }
        }
    }

    inner class DateViewHolder(private val ui: ScheduleAdapterDateUi) : BaseViewHolder<String>(ui.view) {
        override fun bindItem(item: String) {
            ui.date.text = item
        }
    }

    companion object {
        const val VIEW_TYPE_EVENT = 0
        const val VIEW_TYPE_DATE = 1
    }
}