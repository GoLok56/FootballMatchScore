package io.github.golok56.footballmatchscore.teamdetail

data class TeamDetailViewState(
    var loading: Boolean,
    var data: MutableMap<String, Any?>,
    var error: String? = null
)