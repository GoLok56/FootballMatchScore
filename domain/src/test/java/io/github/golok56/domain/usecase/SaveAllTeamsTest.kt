package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.repository.LeagueRepositoryTest
import io.github.golok56.domain.repository.TeamRepositoryTest
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class SaveAllTeamsTest {
    private lateinit var teamRepository: TeamRepositoryTest
    private lateinit var saveAllTeams: SaveAllTeams

    @Before
    fun setUp() {
        teamRepository = TeamRepositoryTest()
        saveAllTeams = SaveAllTeams(teamRepository, Dispatchers.Unconfined)
    }

    @Test
    fun shouldSaveAllTeam() {
        assertEquals(0, teamRepository.teams.size)

        val teams = mutableListOf<TeamEntity>(
            Mockito.mock(TeamEntity::class.java),
            Mockito.mock(TeamEntity::class.java),
            Mockito.mock(TeamEntity::class.java),
            Mockito.mock(TeamEntity::class.java),
            Mockito.mock(TeamEntity::class.java),
            Mockito.mock(TeamEntity::class.java)
        )
        saveAllTeams.execute(teams) { result, error ->
            assertNotNull(result)
            assertNull(error)
            assertEquals(6, teamRepository.teams.size)
        }
    }

    @Test
    fun shouldThrowException() {
        val team = Mockito.mock(TeamEntity::class.java)
        Mockito.`when`(team.id).thenReturn(TeamRepositoryTest.INVALID)
        val teams = mutableListOf<TeamEntity>(team)

        saveAllTeams.execute(teams) { result, err ->
            assertNull(result)
            assertNotNull(err)
            assertEquals(LeagueRepositoryTest.ERROR_MESSAGE, err?.message)
        }
    }
}