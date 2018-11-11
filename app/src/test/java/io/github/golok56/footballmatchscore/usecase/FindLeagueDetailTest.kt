package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.repository.LeagueRepository
import io.github.golok56.footballmatchscore.webservice.LeagueService
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

class FindLeagueDetailTest {
    private lateinit var repo: LeagueRepository
    private lateinit var findLeagueDetail: FindLeagueDetail

    @Before
    fun setup() {
        repo = Mockito.mock(LeagueRepository::class.java)
        findLeagueDetail = FindLeagueDetail(repo)
    }

    @Test
    fun executeTest_returnSuccessResponse() {
        val league = League("test", "test", "test", "test", "test")
        GlobalScope.launch {
            Mockito.`when`(repo.findLeague(ArgumentMatchers.anyString()).await())
                .thenReturn(Response.success(LeagueService.LeagueResponse(mutableListOf(league))))

            val detail = findLeagueDetail.execute(ArgumentMatchers.anyString())
            Assert.assertEquals("test", detail?.id)
        }
    }

    @Test(expected = Exception::class)
    fun executeTest_returnErrorResponse() {
        runBlocking {
            Mockito.`when`(repo.findLeague(ArgumentMatchers.anyString()).await())
                .thenReturn(Response.error(500, ResponseBody.create(ArgumentMatchers.any(), ArgumentMatchers.anyString())))

            findLeagueDetail.execute(ArgumentMatchers.anyString())
        }
    }
}