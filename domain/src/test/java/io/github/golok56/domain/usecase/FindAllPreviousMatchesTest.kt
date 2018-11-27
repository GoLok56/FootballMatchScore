package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.repository.ScheduleRepositoryTest
import io.github.golok56.domain.repository.ScheduleRepositoryTest.Companion.ERROR_MESSAGE
import io.github.golok56.domain.repository.ScheduleRepositoryTest.Companion.INVALID
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class FindAllPreviousMatchesTest {
    private lateinit var scheduleRepository: ScheduleRepositoryTest
    private lateinit var findAllPreviousMatches: FindAllPreviousMatches

    @Before
    fun setUp() {
        scheduleRepository = ScheduleRepositoryTest()
        findAllPreviousMatches = FindAllPreviousMatches(scheduleRepository, Dispatchers.Unconfined)

        scheduleRepository.schedules.apply {
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
            add(mock(ScheduleEntity::class.java))
        }
    }

    @Test
    fun shouldReturn15Matches() {
        findAllPreviousMatches.execute("Ada") { result, err ->
            assertNotNull(result)
            assertEquals(15, result?.size)
            assertNull(err)
        }
    }

    @Test
    fun shouldThrowException() {
        findAllPreviousMatches.execute(INVALID) { result, err ->
            assertNull(result)
            assertNotNull(err)
            assertEquals(ERROR_MESSAGE, err?.message)
        }
    }
}