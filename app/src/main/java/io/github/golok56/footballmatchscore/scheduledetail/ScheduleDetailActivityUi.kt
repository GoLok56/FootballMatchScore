package io.github.golok56.footballmatchscore.scheduledetail

import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import io.github.golok56.footballmatchscore.R
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.nestedScrollView

class ScheduleDetailActivityUi : AnkoComponent<ScheduleDetailActivity> {
    lateinit var date: TextView
    lateinit var score: TextView
    lateinit var homeTeam: TextView
    lateinit var awayTeam: TextView
    lateinit var homeGk: TextView
    lateinit var homeDf: TextView
    lateinit var homeMf: TextView
    lateinit var homeFw: TextView
    lateinit var homeSub: TextView
    lateinit var awayGk: TextView
    lateinit var awayDf: TextView
    lateinit var awayMf: TextView
    lateinit var awayFw: TextView
    lateinit var awaySub: TextView
    lateinit var homeLogo: ImageView
    lateinit var awayLogo: ImageView

    override fun createView(ui: AnkoContext<ScheduleDetailActivity>) = with(ui) {
        relativeLayout {
            padding = dip(16)

            date = textView { id = R.id.date }.lparams {
                alignParentTop()
                centerHorizontally()
            }

            homeLogo = imageView { id = R.id.homeLogo }.lparams(dip(50), dip(50)) {
                below(date)
                topMargin = dip(16)
            }
            homeTeam = textView {
                id = R.id.homeTeam
                gravity = Gravity.CENTER
            }.lparams {
                below(homeLogo)
                sameStart(homeLogo)
                sameEnd(homeLogo)
            }

            awayLogo = imageView { id = R.id.awayLogo }.lparams(dip(50), dip(50)) {
                sameTop(homeLogo)
                alignParentEnd()
            }
            awayTeam = textView {
                id = R.id.awayTeam
                gravity = Gravity.CENTER
            }.lparams {
                below(awayLogo)
                sameStart(awayLogo)
                sameEnd(awayLogo)
            }

            score = textView { id = R.id.score }.lparams {
                centerHorizontally()
                sameTop(homeLogo)
                sameBottom(homeTeam)
            }

            nestedScrollView {
                id = R.id.lineup

                verticalLayout {
                    textView("Home Lineup").lparams { topMargin = dip(8) }
                    textView("GK:").lparams { topMargin = dip(8) }
                    homeGk = textView().lparams { topMargin = dip(4) }
                    textView("DF:").lparams { topMargin = dip(8) }
                    homeDf = textView().lparams { topMargin = dip(4) }
                    textView("MF:").lparams { topMargin = dip(8) }
                    homeMf = textView().lparams { topMargin = dip(4) }
                    textView("FW:").lparams { topMargin = dip(8) }
                    homeFw = textView().lparams { topMargin = dip(4) }
                    textView("SUB:").lparams { topMargin = dip(8) }
                    homeSub = textView().lparams { topMargin = dip(4) }


                    textView("Away Lineup").lparams { topMargin = dip(8) }
                    textView("GK:").lparams { topMargin = dip(8) }
                    awayGk = textView().lparams { topMargin = dip(4) }
                    textView("DF:").lparams { topMargin = dip(8) }
                    awayDf = textView().lparams { topMargin = dip(4) }
                    textView("MF:").lparams { topMargin = dip(8) }
                    awayMf = textView().lparams { topMargin = dip(4) }
                    textView("FW:").lparams { topMargin = dip(8) }
                    awayFw = textView().lparams { topMargin = dip(4) }
                    textView("SUB:").lparams { topMargin = dip(8) }
                    awaySub = textView().lparams { topMargin = dip(4) }
                }
            }.lparams {
                below(score)
                topMargin = dip(16)
            }
        }
    }
}