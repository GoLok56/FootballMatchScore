package io.github.golok56.domain.repository

interface DataStore<Item> {
    suspend fun getAll(data: String): MutableList<Item>
    suspend fun get(data: String): Item?
    suspend fun save(item: Item)
    suspend fun remove(item: Item)
}