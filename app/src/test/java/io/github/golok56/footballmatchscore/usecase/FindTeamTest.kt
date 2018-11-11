package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.repository.TeamRepository
import io.github.golok56.footballmatchscore.webservice.TeamService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import retrofit2.Response

class FindTeamTest {
    private lateinit var repo: TeamRepository
    private lateinit var findTeam: FindTeam

    @Before
    fun setup() {
        repo = Mockito.mock(TeamRepository::class.java)
        findTeam = FindTeam(repo)
    }

    @Test
    fun executeTest_returnSuccessResponse() {
        GlobalScope.launch {
            Mockito.`when`(repo.findTeam(ArgumentMatchers.anyString()).await())
                .thenReturn(Response.success(TeamService.TeamResponse(mutableListOf())))

            findTeam.execute(ArgumentMatchers.anyString())
            Mockito.verify(repo, Mockito.times(1)).save(ArgumentMatchers.any())
        }
    }

    @Test(expected = Exception::class)
    fun executeTest_returnErrorResponse() {
        runBlocking {
            Mockito.`when`(repo.findTeam(ArgumentMatchers.anyString()).await())
                .thenReturn(Response.error(500, ResponseBody.create(ArgumentMatchers.any(), ArgumentMatchers.anyString())))

            findTeam.execute(ArgumentMatchers.anyString())
        }
    }
}