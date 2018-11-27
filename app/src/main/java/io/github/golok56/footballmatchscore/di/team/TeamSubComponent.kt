package io.github.golok56.footballmatchscore.di.team

import dagger.Subcomponent
import io.github.golok56.footballmatchscore.team.TeamFragment

@TeamScope
@Subcomponent(modules = [TeamModule::class])
interface TeamSubComponent {
    fun inject(fragment: TeamFragment)
}