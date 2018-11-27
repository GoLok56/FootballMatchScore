package io.github.golok56.footballmatchscore.di.main

import dagger.Subcomponent
import io.github.golok56.footballmatchscore.main.MainActivity

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainSubComponent {
    fun inject(activity: MainActivity)
}