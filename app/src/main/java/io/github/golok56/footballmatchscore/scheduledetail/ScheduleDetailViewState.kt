package io.github.golok56.footballmatchscore.scheduledetail

data class ScheduleDetailViewState(
    var loading: Boolean,
    var data: MutableMap<String, Any?>,
    var error: String? = null
)