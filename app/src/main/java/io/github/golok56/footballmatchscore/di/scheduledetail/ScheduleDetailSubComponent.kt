package io.github.golok56.footballmatchscore.di.scheduledetail

import dagger.Subcomponent
import io.github.golok56.footballmatchscore.scheduledetail.ScheduleDetailActivity

@ScheduleDetailScope
@Subcomponent(modules = [ScheduleDetailModule::class])
interface ScheduleDetailSubComponent {
    fun inject(activity: ScheduleDetailActivity)
}