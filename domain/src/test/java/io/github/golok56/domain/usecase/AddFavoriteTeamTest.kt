package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.repository.TeamRepositoryTest
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class AddFavoriteTeamTest {
    private lateinit var teamFavoriteRepository: TeamRepositoryTest
    private lateinit var addFavorite: AddFavoriteTeam

    @Before
    fun setUp() {
        teamFavoriteRepository = TeamRepositoryTest()
        addFavorite = AddFavoriteTeam(teamFavoriteRepository, Dispatchers.Unconfined)
    }

    @Test
    fun shouldSaveNewFavoriteTeam() {
        assertEquals(0, teamFavoriteRepository.teams.size)

        val team = Mockito.mock(TeamEntity::class.java)
        addFavorite.execute(team) { result, error ->
            assertNotNull(result)
            assertNull(error)
        }
        assertEquals(1, teamFavoriteRepository.teams.size)
    }

    @Test
    fun shouldThrowException() {
        val team = Mockito.mock(TeamEntity::class.java)
        Mockito.`when`(team.id).thenReturn(TeamRepositoryTest.INVALID)

        addFavorite.execute(team) { result, err ->
            assertNull(result)
            assertNotNull(err)
            assertEquals(TeamRepositoryTest.ERROR_MESSAGE, err?.message)
        }
    }
}