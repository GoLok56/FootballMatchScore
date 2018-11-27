package io.github.golok56.domain.repository

import io.github.golok56.domain.entities.ScheduleEntity

class ScheduleRepositoryTest : Repository<ScheduleEntity> {
    var schedules = mutableListOf<ScheduleEntity>()

    override suspend fun remove(item: ScheduleEntity): Boolean {
        if (item.id == INVALID) throw Exception(ERROR_MESSAGE)
        schedules = schedules.filter { it.id != item.id }.toMutableList()
        return true
    }

    override suspend fun getAll(data: String): MutableList<ScheduleEntity> {
        if (data == INVALID) throw Exception(ERROR_MESSAGE)
        return schedules
    }

    override suspend fun get(data: String): ScheduleEntity? {
        if (data == INVALID) throw Exception(ERROR_MESSAGE)
        return schedules.find { it.id == data }
    }

    override suspend fun save(item: ScheduleEntity): Boolean {
        if (item.id == INVALID) throw Exception(ERROR_MESSAGE)
        schedules.add(item)
        return true
    }

    companion object {
        const val INVALID = "-1"
        const val ERROR_MESSAGE = "Error id tidak boleh kurang dari 0"
    }
}