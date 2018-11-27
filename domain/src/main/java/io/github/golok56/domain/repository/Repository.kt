package io.github.golok56.domain.repository

interface Repository<Item> {
    suspend fun getAll(data: String): MutableList<Item>
    suspend fun get(data: String): Item?
    suspend fun save(item: Item): Boolean
    suspend fun remove(item: Item): Boolean
}