package io.github.golok56.data.repository.upcomingschedule

import io.github.golok56.data.entities.ScheduleData
import io.github.golok56.domain.repository.Cache

class UpcomingScheduleCache : Cache<ScheduleData> {
    private val schedules = mutableMapOf<String, MutableList<ScheduleData>>()

    override fun isEmpty() = schedules.isEmpty()

    override fun isExist(item: ScheduleData): Boolean {
        item.leagueId?.let { schedules.containsKey(it) }
        throw Exception("Terjadi kesalahan, id liga dari item bernilai null")
    }

    override fun isExpired(item: ScheduleData) = false

    override fun put(item: ScheduleData): Boolean {
        item.leagueId?.let { schedules[it]?.add(item) }
        throw Exception("Terjadi kesalahan, id liga dari item bernilai null")
    }

    override fun get(id: String): Nothing? = null

    override fun getAll(): MutableList<ScheduleData> {
        val res = mutableListOf<ScheduleData>()
        schedules.values.forEach {
            it.forEach { schedule -> res.add(schedule) }
        }
        return res
    }

    fun getAll(id: String) = schedules[id]

    override fun clear() = schedules.clear()

    override fun remove(item: ScheduleData) {
        item.leagueId?.let { schedules.remove(it) }
        throw Exception("Terjadi kesalahan, id liga dari item bernilai null")
    }
}
