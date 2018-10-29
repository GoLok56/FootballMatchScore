package io.github.golok56.footballmatchscore.schedule

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

class ScheduleActivityUi(private val schedulePagerAdapter: SchedulePagerAdapter) : AnkoComponent<ScheduleActivity> {
    lateinit var vp: ViewPager
    lateinit var tl: TabLayout

    override fun createView(ui: AnkoContext<ScheduleActivity>) = with(ui) {
        verticalLayout {
            tl = tabLayout ()
            vp = viewPager {
                id = 1
                adapter = schedulePagerAdapter
            }

            tl.setupWithViewPager(vp)
        }
    }
}