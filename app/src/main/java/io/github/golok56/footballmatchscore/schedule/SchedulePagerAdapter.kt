package io.github.golok56.footballmatchscore.schedule

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.github.golok56.footballmatchscore.schedule.last.LastMatchFragment
import io.github.golok56.footballmatchscore.schedule.next.NextMatchFragment

class SchedulePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(pos: Int) = when(pos) {
        0 -> LastMatchFragment()
        else -> NextMatchFragment()
    }
    override fun getCount() = 2
    override fun getPageTitle(position: Int) = when(position) {
        0 -> "Last 15 Match"
        else -> "Next 15 Match"
    }
}
