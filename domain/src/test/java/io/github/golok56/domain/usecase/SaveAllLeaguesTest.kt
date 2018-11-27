package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.LeagueEntity
import io.github.golok56.domain.repository.LeagueRepositoryTest
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class SaveAllLeaguesTest {
    private lateinit var leagueRepository: LeagueRepositoryTest
    private lateinit var saveAllLeagues: SaveAllLeagues

    @Before
    fun setUp() {
        leagueRepository = LeagueRepositoryTest()
        saveAllLeagues = SaveAllLeagues(leagueRepository, Dispatchers.Unconfined)
    }

    @Test
    fun shouldSaveNewFavoriteSchedule() {
        assertEquals(0, leagueRepository.leagues.size)

        val leagues = mutableListOf<LeagueEntity>(
            mock(LeagueEntity::class.java),
            mock(LeagueEntity::class.java),
            mock(LeagueEntity::class.java),
            mock(LeagueEntity::class.java),
            mock(LeagueEntity::class.java),
            mock(LeagueEntity::class.java)
        )
        saveAllLeagues.execute(leagues) { result, error ->
            assertNotNull(result)
            assertNull(error)
            assertEquals(6, leagueRepository.leagues.size)
        }
    }

    @Test
    fun shouldThrowException() {
        val league = Mockito.mock(LeagueEntity::class.java)
        Mockito.`when`(league.id).thenReturn(LeagueRepositoryTest.INVALID)
        val leagues = mutableListOf<LeagueEntity>(league)

        saveAllLeagues.execute(leagues) { result, err ->
            assertNull(result)
            assertNotNull(err)
            assertEquals(LeagueRepositoryTest.ERROR_MESSAGE, err?.message)
        }
    }
}