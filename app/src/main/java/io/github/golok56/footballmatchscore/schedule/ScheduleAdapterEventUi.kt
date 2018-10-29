package io.github.golok56.footballmatchscore.schedule

import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*

class ScheduleAdapterEventUi(ankoContext: AnkoContext<ViewGroup>) : AnkoComponent<ViewGroup> {
    val view = createView(ankoContext)
    lateinit var homeTeam: TextView
    lateinit var awayTeam: TextView
    lateinit var score: TextView

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout {
            lparams(matchParent, wrapContent)

            orientation = LinearLayout.HORIZONTAL
            padding = dip(16)

            homeTeam = textView { gravity = Gravity.CENTER }.lparams(dip(0), wrapContent) { weight = 1f }

            score = textView().lparams(wrapContent, wrapContent)

            awayTeam = textView { gravity = Gravity.CENTER }.lparams(dip(0), wrapContent) { weight = 1f }
        }
    }
}
