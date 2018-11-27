package io.github.golok56.domain.mapper

interface Mapper<From, To> {
    fun map(from: From): To
}