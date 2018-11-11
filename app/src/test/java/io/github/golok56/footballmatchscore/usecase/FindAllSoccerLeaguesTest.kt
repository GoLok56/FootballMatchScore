package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.repository.LeagueRepository
import io.github.golok56.footballmatchscore.webservice.LeagueService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import retrofit2.Response

class FindAllSoccerLeaguesTest {
    private lateinit var repo: LeagueRepository
    private lateinit var findAllSoccerLeagues: FindAllSoccerLeagues

    @Before
    fun setup() {
        repo = Mockito.mock(LeagueRepository::class.java)
        findAllSoccerLeagues = FindAllSoccerLeagues(repo)
    }

    @Test
    fun executeTest_returnSuccessResponse() {
        GlobalScope.launch(Dispatchers.Unconfined) {
            Mockito.`when`(repo.findAllLeagues().await())
                .thenReturn(Response.success(LeagueService.LeagueResponse(mutableListOf())))

            val leagues = findAllSoccerLeagues.execute(ArgumentMatchers.anyBoolean())
            leagues?.forEach { Assert.assertEquals("Soccer", it.sport) }
        }
    }

    @Test(expected = Exception::class)
    fun executeTest_returnErrorResponse() {
        runBlocking {
            Mockito.`when`(repo.findAllLeagues().await())
                .thenReturn(Response.error(500, ResponseBody.create(ArgumentMatchers.any(), ArgumentMatchers.anyString())))

            findAllSoccerLeagues.execute(ArgumentMatchers.anyBoolean())
        }
    }
}