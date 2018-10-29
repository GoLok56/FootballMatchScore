package io.github.golok56.footballmatchscore.schedule.next

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.golok56.footballmatchscore.schedule.ScheduleAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class NextMatchFragmentUi(private val scheduleAdapter: ScheduleAdapter) : AnkoComponent<ViewGroup> {
    lateinit var error: TextView
    lateinit var matchList: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        verticalLayout {
            swipeRefreshLayout = swipeRefreshLayout {
                frameLayout {
                    matchList = recyclerView {
                        adapter = scheduleAdapter
                        layoutManager = LinearLayoutManager(context)
                    }.lparams(matchParent, matchParent)

                    error = textView {
                        textSize = 18f
                        visibility = View.GONE
                        gravity = Gravity.CENTER
                    }.lparams(matchParent, wrapContent) {
                        margin = dip(16)
                        gravity = Gravity.CENTER
                    }
                }
            }.lparams(matchParent, matchParent)
        }
    }
}