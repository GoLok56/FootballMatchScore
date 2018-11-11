package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.repository.NextScheduleRepository
import io.github.golok56.footballmatchscore.webservice.ScheduleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import retrofit2.Response

class FindAllNextMatchesTest {
    private lateinit var repo: NextScheduleRepository
    private lateinit var findAllNextMatches: FindAllNextMatches

    @Before
    fun setup() {
        repo = Mockito.mock(NextScheduleRepository::class.java)
        findAllNextMatches = FindAllNextMatches(repo)
    }

    @Test
    fun executeTest_returnSuccessResponse() {
        GlobalScope.launch(Dispatchers.Unconfined) {
            Mockito.`when`(repo.findAllNextMatches(ArgumentMatchers.anyString()).await())
                .thenReturn(Response.success(ScheduleService.ScheduleResponse(mutableListOf())))

            findAllNextMatches.execute(ArgumentMatchers.anyString())
            Mockito.verify(repo, Mockito.times(1)).save(ArgumentMatchers.any())
        }
    }

    @Test(expected = Exception::class)
    fun executeTest_returnErrorResponse() {
        runBlocking {
            Mockito.`when`(repo.findAllNextMatches(ArgumentMatchers.anyString()).await())
                .thenReturn(Response.error(500, ResponseBody.create(ArgumentMatchers.any(), ArgumentMatchers.anyString())))

            findAllNextMatches.execute(ArgumentMatchers.anyString())
        }
    }
}