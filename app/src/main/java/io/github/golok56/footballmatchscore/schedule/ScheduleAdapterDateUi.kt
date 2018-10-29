package io.github.golok56.footballmatchscore.schedule

import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*

class ScheduleAdapterDateUi(ankoContext: AnkoContext<ViewGroup>) : AnkoComponent<ViewGroup> {
    val view = createView(ankoContext)
    lateinit var date: TextView
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        verticalLayout {
            lparams(matchParent, wrapContent)

            date = textView  {
                typeface = Typeface.DEFAULT_BOLD
            }.lparams { gravity = Gravity.CENTER_HORIZONTAL }
        }
    }
}