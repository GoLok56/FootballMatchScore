package io.github.golok56.domain.repository

interface Cache<Item> {
    fun isEmpty(): Boolean
    fun isExist(item: Item): Boolean
    fun isExpired(item: Item): Boolean
    fun put(item: Item): Boolean
    fun get(id: String): Item?
    fun getAll(): MutableList<Item>
    fun clear()
    fun remove(item: Item)
}