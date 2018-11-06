package io.github.golok56.footballmatchscore.schedule

class SchedulePagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {
    override fun getItem(pos: Int) = when(pos) {
        0 -> SchedulePreviousFragment()
        else -> ScheduleUpcomingFragment()
    }
    override fun getCount() = 2
    override fun getPageTitle(position: Int) = when(position) {
        0 -> "Last 15 Match"
        else -> "Next 15 Match"
    }
}
