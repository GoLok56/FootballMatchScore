package io.github.golok56.footballmatchscore.usecase

import io.github.golok56.footballmatchscore.model.League
import io.github.golok56.footballmatchscore.repository.LeagueRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

class SaveAllLeaguesTest {
    @Test
    fun executeTest() {
        val repo = mock(LeagueRepository::class.java)
        val items = mutableListOf<League>()
        val saveAllLeagues = SaveAllLeagues(repo)

        GlobalScope.launch {
            saveAllLeagues.execute(items)
            verify(repo, times(items.size)).save(ArgumentMatchers.any())

            items.add(ArgumentMatchers.any())
            items.add(ArgumentMatchers.any())
            items.add(ArgumentMatchers.any())

            verify(repo, times(items.size)).save(ArgumentMatchers.any())
        }
    }
}