package io.github.golok56.footballmatchscore.di.base

import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.golok56.data.services.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
class AppModule(private val context: Context) {
    @Provides
    fun providesContext() = context

    @Provides
    fun providesDatabaseHelper() = context.getDatabase()

    @Provides
    fun providesCoroutineContext(): CoroutineContext = Dispatchers.Main
}