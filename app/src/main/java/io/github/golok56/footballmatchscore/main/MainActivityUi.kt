package io.github.golok56.footballmatchscore.main

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivityUi(private val mainAdapter: MainAdapter) : AnkoComponent<MainActivity> {
    lateinit var error: TextView
    lateinit var leagueList: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            swipeRefreshLayout = swipeRefreshLayout {
                frameLayout {
                    leagueList = recyclerView {
                        adapter = mainAdapter
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