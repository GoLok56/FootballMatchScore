package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.repository.ScheduleRepositoryTest
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RemoveFavoriteTest {
    private lateinit var scheduleRepository: ScheduleRepositoryTest
    private lateinit var removeFavorite: RemoveFavorite

    @Before
    fun setUp() {
        scheduleRepository = ScheduleRepositoryTest()
        removeFavorite = RemoveFavorite(scheduleRepository, Dispatchers.Unconfined)

        scheduleRepository.schedules.apply {
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))

            val schedule = mock(ScheduleEntity::class.java)
            `when`(schedule.id).thenReturn("ada")
            add(schedule)
        }
    }

    @Test
    fun shouldSaveNewFavoriteSchedule() {
        assertEquals(9, scheduleRepository.schedules.size)

        val schedule = Mockito.mock(ScheduleEntity::class.java)
        `when`(schedule.id).thenReturn("ada")
        removeFavorite.execute(schedule) { result, error ->
            assertNotNull(result)
            assertNull(error)
        }
        assertEquals(8, scheduleRepository.schedules.size)
    }

    @Test
    fun shouldThrowException() {
        val schedule = Mockito.mock(ScheduleEntity::class.java)
        Mockito.`when`(schedule.id).thenReturn(ScheduleRepositoryTest.INVALID)

        removeFavorite.execute(schedule) { result, err ->
            assertNull(result)
            assertNotNull(err)
            assertEquals(ScheduleRepositoryTest.ERROR_MESSAGE, err?.message)
        }
    }
}