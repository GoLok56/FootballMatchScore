package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.repository.ScheduleRepositoryTest
import io.github.golok56.domain.repository.ScheduleRepositoryTest.Companion.ERROR_MESSAGE
import io.github.golok56.domain.repository.ScheduleRepositoryTest.Companion.INVALID
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class AddFavoriteTest {
    private lateinit var scheduleRepository: ScheduleRepositoryTest
    private lateinit var addFavorite: AddFavorite

    @Before
    fun setUp() {
        scheduleRepository = ScheduleRepositoryTest()
        addFavorite = AddFavorite(scheduleRepository, Dispatchers.Unconfined)
    }

    @Test
    fun shouldSaveNewFavoriteSchedule() {
        assertEquals(0, scheduleRepository.schedules.size)

        val schedule = mock(ScheduleEntity::class.java)
        addFavorite.execute(schedule) { result, error ->
            assertNotNull(result)
            assertNull(error)
        }
        assertEquals(1, scheduleRepository.schedules.size)
    }

    @Test
    fun shouldThrowException() {
        val schedule = mock(ScheduleEntity::class.java)
        `when`(schedule.id).thenReturn(INVALID)

        addFavorite.execute(schedule) { result, err ->
            assertNull(result)
            assertNotNull(err)
            assertEquals(ERROR_MESSAGE, err?.message)
        }
    }
}