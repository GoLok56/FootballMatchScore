package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.repository.PreviousScheduleRepository
import io.github.golok56.footballmatchscore.webservice.ScheduleService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import retrofit2.Response

class FindAllLastMatchesTest {
    private lateinit var repo: PreviousScheduleRepository
    private lateinit var findAllLastMatches: FindAllLastMatches

    @Before
    fun setup() {
        repo = mock(PreviousScheduleRepository::class.java)
        findAllLastMatches = FindAllLastMatches(repo)
    }

    @Test
    fun executeTest_returnSuccessResponse() {
        GlobalScope.launch {
            `when`(repo.findAllLastMatches(ArgumentMatchers.anyString()).await())
                .thenReturn(Response.success(ScheduleService.ScheduleResponse(mutableListOf())))

            findAllLastMatches.execute(ArgumentMatchers.anyString())
            verify(repo, times(1)).save(ArgumentMatchers.any())
        }
    }

    @Test(expected = Exception::class)
    fun executeTest_returnErrorResponse() {
        runBlocking {
            `when`(repo.findAllLastMatches(ArgumentMatchers.anyString()).await())
                .thenReturn(Response.error(500, ResponseBody.create(ArgumentMatchers.any(), ArgumentMatchers.anyString())))

            findAllLastMatches.execute(ArgumentMatchers.anyString())
        }
    }
}