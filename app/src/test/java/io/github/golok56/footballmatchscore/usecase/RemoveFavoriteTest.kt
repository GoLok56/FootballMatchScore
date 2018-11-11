package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.repository.FavoriteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test
import org.mockito.Mockito

class RemoveFavoriteTest {
    @Test
    fun executeTest() {
        val repo = Mockito.mock(FavoriteRepository::class.java)
        val item = Mockito.mock(Schedule::class.java)

        val removeFavorite = RemoveFavorite(repo)

        GlobalScope.launch {
            removeFavorite.execute(item)
            removeFavorite.execute(item)
            Mockito.verify(repo, Mockito.times(2)).remove(item)
        }
    }
}