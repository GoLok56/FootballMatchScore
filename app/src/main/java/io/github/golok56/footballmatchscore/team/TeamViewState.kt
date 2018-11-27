package io.github.golok56.footballmatchscore.team

import io.github.golok56.footballmatchscore.model.Team

data class TeamViewState(
    var loading: Boolean = true,
    var data: MutableMap<String, MutableList<Team>?> = mutableMapOf(),
    var error: String? = null
)
