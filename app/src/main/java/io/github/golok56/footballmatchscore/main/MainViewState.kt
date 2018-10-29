package io.github.golok56.footballmatchscore.main

import io.github.golok56.footballmatchscore.model.League

data class MainViewState(
    val loading: Boolean,
    val data: MutableList<League>?,
    val error: String? = null
)