package io.github.golok56.footballmatchscore.main

import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class MainAdapterUi(ankoContext: AnkoContext<ViewGroup>) : AnkoComponent<ViewGroup> {
    val view = createView(ankoContext)
    lateinit var leagueName: TextView

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        cardView {
            lparams(matchParent, wrapContent) {
                topMargin = dip(1)
                bottomMargin = dip(1)
            }
            verticalLayout {
                padding = dip(16)
                leagueName = textView()
            }.lparams(matchParent, wrapContent)
        }
    }
}