package io.github.golok56.footballmatchscore.model

data class League (
    var id: String?,
    var name: String?,
    var sport: String?,
    var alternateName: String?,
    var logo: String?,
    var updatedOn: Long? = null
)