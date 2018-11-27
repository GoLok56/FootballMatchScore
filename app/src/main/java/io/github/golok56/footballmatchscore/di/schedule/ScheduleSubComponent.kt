package io.github.golok56.footballmatchscore.di.schedule

import dagger.Subcomponent
import io.github.golok56.footballmatchscore.schedule.ScheduleFavoriteFragment
import io.github.golok56.footballmatchscore.schedule.SchedulePreviousFragment
import io.github.golok56.footballmatchscore.schedule.ScheduleUpcomingFragment

@ScheduleScope
@Subcomponent(modules = [ScheduleModule::class])
interface ScheduleSubComponent {
    fun inject(fragment: ScheduleFavoriteFragment)
    fun inject(fragment: SchedulePreviousFragment)
    fun inject(fragment: ScheduleUpcomingFragment)
}