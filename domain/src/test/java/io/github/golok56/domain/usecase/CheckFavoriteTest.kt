package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.repository.ScheduleRepositoryTest
import io.github.golok56.domain.repository.ScheduleRepositoryTest.Companion.ERROR_MESSAGE
import io.github.golok56.domain.repository.ScheduleRepositoryTest.Companion.INVALID
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CheckFavoriteTest {
    private lateinit var scheduleRepository: ScheduleRepositoryTest
    private lateinit var checkFavorite: CheckFavorite

    @Before
    fun setUp() {
        scheduleRepository = ScheduleRepositoryTest()
        checkFavorite = CheckFavorite(scheduleRepository, Dispatchers.Unconfined)
    }

    @Test
    fun shouldReturnTrueIfItemIsExist() {
        val schedule = mock(ScheduleEntity::class.java)
        `when`(schedule.id).thenReturn("Ada")

        scheduleRepository.schedules.add(schedule)

        checkFavorite.execute(schedule.id!!) { result, error ->
            assertNotNull(result)
            assertEquals(true, result)
            assertNull(error)
        }
    }

    @Test
    fun shouldReturnFalseIfItemIsNotExist() {
        val schedule = mock(ScheduleEntity::class.java)
        `when`(schedule.id).thenReturn("Ga ada")
        checkFavorite.execute(schedule.id!!) { result, error ->
            assertNotNull(result)
            assertEquals(false, result)
            assertNull(error)
        }
    }

    @Test
    fun shouldThrowException() {
        val schedule = mock(ScheduleEntity::class.java)
        `when`(schedule.id).thenReturn(INVALID)

        checkFavorite.execute(schedule.id!!) { result, error ->
            assertNull(result)
            assertNotNull(error)
            assertEquals(ERROR_MESSAGE, error?.message)
        }
    }
}