package io.github.golok56.footballmatchscore.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.golok56.footballmatchscore.R
import io.github.golok56.footballmatchscore.base.BaseViewHolder
import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.scheduledetail.ScheduleDetailActivity
import kotlinx.android.synthetic.main.item_schedule_date.view.*
import kotlinx.android.synthetic.main.item_schedule_event.view.*
import org.jetbrains.anko.startActivity

class ScheduleAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {
    private val items = mutableListOf<Any>()

    override fun getItemViewType(position: Int) = when (items[position]) {
        is String -> VIEW_TYPE_DATE
        is Schedule -> VIEW_TYPE_EVENT
        else -> VIEW_TYPE_INVALID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        VIEW_TYPE_EVENT -> EventViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_schedule_event, parent, false))
        else -> DateViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_schedule_date, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, pos: Int) {
        when (holder) {
            is EventViewHolder -> holder.bindItem(items[pos] as Schedule)
            is DateViewHolder -> holder.bindItem(items[pos] as String)
        }
    }

    fun updateData(data: MutableList<Schedule>) {
        items.clear()
        updateItems(data)
        notifyDataSetChanged()
    }

    private fun updateItems(data: MutableList<Schedule>) {
        var currentDate = ""
        data.forEach {
            it.date?.let { date ->
                if (date != currentDate) {
                    currentDate = date
                    items.add(currentDate)
                }
            }

            items.add(it)
        }
    }

    inner class EventViewHolder(view: View) : BaseViewHolder<Schedule>(view) {
        override fun bindItem(item: Schedule) {
            itemView.tvScheduleHomeTeam.text = item.homeTeam
            itemView.tvScheduleAwayTeam.text = item.awayTeam
            itemView.tvScheduleScore.text = itemView.context.resources.getString(R.string.label_versus,
                    item.homeScore ?: "", item.awayScore ?: "")
            itemView.setOnClickListener {
                itemView.context.startActivity<ScheduleDetailActivity>(
                        ScheduleDetailActivity.EXTRA_SCHEDULE to item)
            }
        }
    }

    inner class DateViewHolder(view: View) : BaseViewHolder<String>(view) {
        override fun bindItem(item: String) {
            itemView.tvScheduleDate.text = item
        }
    }

    companion object {
        const val VIEW_TYPE_EVENT = 0
        const val VIEW_TYPE_DATE = 1
        const val VIEW_TYPE_INVALID = -1
    }
}