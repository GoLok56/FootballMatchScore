package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.repository.TeamRepositoryTest
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class FindTeamTest {
    private lateinit var teamRepository: TeamRepositoryTest
    private lateinit var findTeam: FindTeam

    @Before
    fun setUp() {
        teamRepository = TeamRepositoryTest()
        findTeam = FindTeam(teamRepository, Dispatchers.Unconfined)
    }

    @Test
    fun shouldReturnTrueIfItemIsExist() {
        val team = Mockito.mock(TeamEntity::class.java)
        Mockito.`when`(team.id).thenReturn("Ada")

        teamRepository.teams.add(team)

        findTeam.execute(team.id!!) { result, error ->
            assertNotNull(result)
            assertNull(error)
        }
    }

    @Test
    fun shouldReturnNullIfItemIsNotExist() {
        val team = Mockito.mock(TeamEntity::class.java)
        Mockito.`when`(team.id).thenReturn("Ga ada")
        findTeam.execute(team.id!!) { result, error ->
            assertNull(result)
            assertNull(error)
        }
    }

    @Test
    fun shouldThrowException() {
        val team = Mockito.mock(TeamEntity::class.java)
        Mockito.`when`(team.id).thenReturn(TeamRepositoryTest.INVALID)

        findTeam.execute(team.id!!) { result, error ->
            assertNull(result)
            assertNotNull(error)
            assertEquals(TeamRepositoryTest.ERROR_MESSAGE, error?.message)
        }
    }
}