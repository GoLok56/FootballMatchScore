package io.github.golok56.footballmatchscore.repository

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.webservice.ApiService
import io.github.golok56.footballmatchscore.webservice.ScheduleService
import kotlinx.coroutines.Deferred
import retrofit2.Response

class NextScheduleRepository private constructor() : Repository<MutableList<Schedule>> {
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

    fun findAllNextMatches(leagueId: String): Deferred<Response<ScheduleService.ScheduleResponse>> {
//        return if (get(leagueId) == null) ApiService.scheduleService.findNextMatches(leagueId)
//        else GlobalScope.async(Dispatchers.Main) {
//            Response.success(ScheduleService.ScheduleResponse(get(leagueId)))
//        }
        return ApiService.scheduleService.findNextMatches(leagueId)
    }

    companion object {
        private var instance: NextScheduleRepository? = null

        fun getInstance(): NextScheduleRepository {
            if (instance == null) {
                instance = NextScheduleRepository()
            }

            return instance!!
        }
    }
}