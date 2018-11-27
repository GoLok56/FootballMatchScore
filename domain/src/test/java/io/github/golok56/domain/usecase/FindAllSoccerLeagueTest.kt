package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.LeagueEntity
import io.github.golok56.domain.repository.LeagueRepositoryTest
import io.github.golok56.domain.repository.LeagueRepositoryTest.Companion.INVALID
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FindAllSoccerLeagueTest {
    private lateinit var leagueRepository: LeagueRepositoryTest
    private lateinit var findAllSoccerLeague: FindAllSoccerLeague

    @Before
    fun setUp() {
        leagueRepository = LeagueRepositoryTest()
        findAllSoccerLeague = FindAllSoccerLeague(leagueRepository, Dispatchers.Unconfined)

        leagueRepository.leagues.apply {
            add(LeagueEntity("", "", "Soccer", "", "", 0))
            add(LeagueEntity("", "", "Soccer", "", "", 0))
            add(LeagueEntity("", "", "Football", "", "", 0))
            add(LeagueEntity("", "", "Soccer", "", "", 0))
            add(LeagueEntity("", "", "Basketball", "", "", 0))
            add(LeagueEntity("", "", "BaseBall", "", "", 0))
            add(LeagueEntity("", "", "Soccer", "", "", 0))
        }
    }

    @Test
    fun shouldOnlyReturnSoccerLeagues() {
        findAllSoccerLeague.execute("") { result, error ->
            assertNotNull(result)
            assertEquals(4, result?.size)
            assertNull(error)
        }
    }

    @Test
    fun shouldThrowException() {
        findAllSoccerLeague.execute(INVALID) { result, error ->
            assertNull(result)
            assertNotNull(error)
        }
    }
}