package io.github.golok56.footballmatchscore.model

import com.google.gson.annotations.SerializedName

data class League (
    @SerializedName("idLeague")
    var id: String,
    @SerializedName("strLeague")
    var name: String,
    @SerializedName("strSport")
    var sport: String,
    @SerializedName("strLeagueAlternate")
    var alternateName: String
)