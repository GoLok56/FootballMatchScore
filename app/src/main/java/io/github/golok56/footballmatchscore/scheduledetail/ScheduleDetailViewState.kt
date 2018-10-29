package io.github.golok56.footballmatchscore.scheduledetail

import io.github.golok56.footballmatchscore.model.Team

data class ScheduleDetailViewState(
    var loading: Boolean,
    var data: MutableMap<String, Team?>,
    var error: String? = null
)