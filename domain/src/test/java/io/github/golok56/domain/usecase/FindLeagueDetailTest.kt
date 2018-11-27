package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.LeagueEntity
import io.github.golok56.domain.repository.LeagueRepositoryTest
import io.github.golok56.domain.repository.LeagueRepositoryTest.Companion.ERROR_MESSAGE
import io.github.golok56.domain.repository.LeagueRepositoryTest.Companion.INVALID
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class FindLeagueDetailTest {
    private lateinit var leagueRepository: LeagueRepositoryTest
    private lateinit var findLeagueDetail: FindLeagueDetail

    @Before
    fun setUp() {
        leagueRepository = LeagueRepositoryTest()
        findLeagueDetail = FindLeagueDetail(leagueRepository, Dispatchers.Unconfined)
    }

    @Test
    fun shouldReturnTrueIfItemIsExist() {
        val league = Mockito.mock(LeagueEntity::class.java)
        Mockito.`when`(league.id).thenReturn("Ada")

        leagueRepository.leagues.add(league)

        findLeagueDetail.execute(league.id!!) { result, error ->
            assertNotNull(result)
            assertNull(error)
        }
    }

    @Test
    fun shouldReturnNullIfItemIsNotExist() {
        val league = Mockito.mock(LeagueEntity::class.java)
        Mockito.`when`(league.id).thenReturn("Ga ada")
        findLeagueDetail.execute(league.id!!) { result, error ->
            assertNull(result)
            assertNull(error)
        }
    }

    @Test
    fun shouldThrowException() {
        val league = Mockito.mock(LeagueEntity::class.java)
        Mockito.`when`(league.id).thenReturn(INVALID)

        findLeagueDetail.execute(league.id!!) { result, error ->
            assertNull(result)
            assertNotNull(error)
            assertEquals(ERROR_MESSAGE, error?.message)
        }
    }
}