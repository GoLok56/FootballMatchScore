package io.github.golok56.footballmatchscore.di.teamdetail

import dagger.Subcomponent
import io.github.golok56.footballmatchscore.teamdetail.TeamDetailActivity
import io.github.golok56.footballmatchscore.teamdetail.TeamDetailPlayerFragment

@Subcomponent(modules = [TeamDetailModule::class])
interface TeamDetailSubComponent {
    fun inject(activity: TeamDetailActivity)
    fun inject(fragment: TeamDetailPlayerFragment)
}