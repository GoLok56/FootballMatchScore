package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.repository.FavoriteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test
import org.mockito.Mockito.*

class AddFavoriteTest {
    @Test
    fun executeTest() {
        val repo = mock(FavoriteRepository::class.java)
        val item = mock(Schedule::class.java)

        val addFavorite = AddFavorite(repo)

        GlobalScope.launch {
            addFavorite.execute(item)
            addFavorite.execute(item)
            verify(repo, times(2)).save(item)
        }
    }
}