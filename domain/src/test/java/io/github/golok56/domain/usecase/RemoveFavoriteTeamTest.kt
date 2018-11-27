package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.repository.TeamRepositoryTest
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class RemoveFavoriteTeamTest {
    private lateinit var teamFavoriteRepository: TeamRepositoryTest
    private lateinit var removeFavorite: RemoveFavoriteTeam

    @Before
    fun setUp() {
        teamFavoriteRepository = TeamRepositoryTest()
        removeFavorite = RemoveFavoriteTeam(teamFavoriteRepository, Dispatchers.Unconfined)

        teamFavoriteRepository.teams.apply {
            add(Mockito.mock(TeamEntity::class.java))
            add(Mockito.mock(TeamEntity::class.java))
            add(Mockito.mock(TeamEntity::class.java))
            add(Mockito.mock(TeamEntity::class.java))
            add(Mockito.mock(TeamEntity::class.java))
            add(Mockito.mock(TeamEntity::class.java))
            add(Mockito.mock(TeamEntity::class.java))
            add(Mockito.mock(TeamEntity::class.java))

            val team = Mockito.mock(TeamEntity::class.java)
            Mockito.`when`(team.id).thenReturn("ada")
            add(team)
        }
    }

    @Test
    fun shouldSaveNewFavoriteSchedule() {
        assertEquals(9, teamFavoriteRepository.teams.size)

        val team = Mockito.mock(TeamEntity::class.java)
        Mockito.`when`(team.id).thenReturn("ada")
        removeFavorite.execute(team) { result, error ->
            assertNotNull(result)
            assertNull(error)
        }
        assertEquals(8, teamFavoriteRepository.teams.size)
    }

    @Test
    fun shouldThrowException() {
        val team = Mockito.mock(TeamEntity::class.java)
        Mockito.`when`(team.id).thenReturn(TeamRepositoryTest.INVALID)

        removeFavorite.execute(team) { result, err ->
            assertNull(result)
            assertNotNull(err)
            assertEquals(TeamRepositoryTest.ERROR_MESSAGE, err?.message)
        }
    }
}