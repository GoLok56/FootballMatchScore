package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.ScheduleEntity
import io.github.golok56.domain.repository.ScheduleRepositoryTest
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class FindAllFavoriteMatchesTest {
    private lateinit var favoriteRepository: ScheduleRepositoryTest
    private lateinit var findAllFavoriteMatches: FindAllFavoriteMatches

    @Before
    fun setUp() {
        favoriteRepository = ScheduleRepositoryTest()
        findAllFavoriteMatches = FindAllFavoriteMatches(favoriteRepository, Dispatchers.Unconfined)

        favoriteRepository.schedules.apply {
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
            add(Mockito.mock(ScheduleEntity::class.java))
        }
    }

    @Test
    fun shouldReturn15Matches() {
        findAllFavoriteMatches.execute("Ada") { result, err ->
            assertNotNull(result)
            assertEquals(15, result?.size)
            assertNull(err)
        }
    }

    @Test
    fun shouldThrowException() {
        findAllFavoriteMatches.execute(ScheduleRepositoryTest.INVALID) { result, err ->
            assertNull(result)
            assertNotNull(err)
            assertEquals(ScheduleRepositoryTest.ERROR_MESSAGE, err?.message)
        }
    }
}