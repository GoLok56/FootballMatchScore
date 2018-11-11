package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.repository.FavoriteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

class CheckFavoriteTest {
    @Test
    fun executeTest() {
        val repo = mock(FavoriteRepository::class.java)
        val checkFavorite = CheckFavorite(repo)

        GlobalScope.launch {
            checkFavorite.execute(ArgumentMatchers.anyString())
            checkFavorite.execute(ArgumentMatchers.anyString())
            verify(repo, times(2)).get(ArgumentMatchers.anyString())
        }
    }
}