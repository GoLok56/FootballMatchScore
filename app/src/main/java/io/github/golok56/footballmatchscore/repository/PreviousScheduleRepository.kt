package io.github.golok56.footballmatchscore.repository

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.webservice.ApiService
import io.github.golok56.footballmatchscore.webservice.ScheduleService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Response

class PreviousScheduleRepository : Repository<MutableList<Schedule>> {
    private var schedules: MutableMap<String, MutableList<Schedule>?> = mutableMapOf()

    override fun clear() {
        schedules.clear()
    }

    override fun save(item: MutableList<Schedule>) {
        item[0].leagueId?.let { schedules[it] = item }
    }

    override fun remove(item: MutableList<Schedule>) {
        item[0].leagueId?.let { schedules.remove(it) }
    }

    override fun get(id: String) = if (schedules.contains(id)) schedules[id] else null
    override fun isExpired(item: MutableList<Schedule>) = true
    override fun getAll(): MutableList<MutableList<Schedule>> = mutableListOf()

    fun findAllLastMatches(leagueId: String): Deferred<Response<ScheduleService.ScheduleResponse>> {
        return if (get(leagueId) == null) ApiService.scheduleService.findLastMatches(leagueId)
        else GlobalScope.async(Dispatchers.Main) {
            Response.success(ScheduleService.ScheduleResponse(get(leagueId)))
        }
    }

    companion object {
        private var instance: PreviousScheduleRepository? = null

        fun getInstance(): PreviousScheduleRepository {
            if (instance == null) {
                instance = PreviousScheduleRepository()
            }

            return instance!!
        }
    }
}