package io.github.golok56.footballmatchscore.repository

interface Repository<T> {
    fun clear()
    fun save(item: T)
    fun remove(item: T)
    fun get(id: String): T?
    fun getAll(): MutableList<T>
    fun isExpired(item: T): Boolean
}