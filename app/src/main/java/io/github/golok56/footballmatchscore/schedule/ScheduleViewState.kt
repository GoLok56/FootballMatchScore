package io.github.golok56.footballmatchscore.schedule

import io.github.golok56.footballmatchscore.model.Schedule

data class ScheduleViewState(
    var loading: Boolean,
    var data: MutableMap<String, MutableList<Schedule>?>,
    var error: String? = null
)