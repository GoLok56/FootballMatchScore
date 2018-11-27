package io.github.golok56.domain.usecase

import io.github.golok56.domain.entities.TeamEntity
import io.github.golok56.domain.repository.TeamRepositoryTest
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class FindAllTeamsTest {
    private lateinit var teamRepository: TeamRepositoryTest
    private lateinit var findAllTeams: FindAllTeams

    @Before
    fun setUp() {
        teamRepository = TeamRepositoryTest()
        findAllTeams = FindAllTeams(teamRepository, Dispatchers.Unconfined)

        teamRepository.teams.apply {
            add(TeamEntity("", "", "", "", "", "", 0))
        }
    }

    @Test
    fun shouldReturnAllTeams() {
        findAllTeams.execute("") { result, error ->
            assertNotNull(result)
            assertEquals(1, result?.size)
            assertNull(error)
        }
    }

    @Test
    fun shouldThrowException() {
        findAllTeams.execute(TeamRepositoryTest.INVALID) { result, error ->
            assertNull(result)
            assertNotNull(error)
        }
    }
}