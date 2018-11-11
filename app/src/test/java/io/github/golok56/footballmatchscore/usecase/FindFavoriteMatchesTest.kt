package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.Schedule
import io.github.golok56.footballmatchscore.repository.FavoriteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

class FindFavoriteMatchesTest {
    @Test
    fun executeTest() {
        val repo = Mockito.mock(FavoriteRepository::class.java)

        val findFavoriteMatches = FindFavoriteMatches(repo)

        GlobalScope.launch {
            findFavoriteMatches.execute(ArgumentMatchers.anyBoolean())
            findFavoriteMatches.execute(ArgumentMatchers.anyBoolean())
            Mockito.verify(repo, Mockito.times(2)).getAll()
        }
    }
}