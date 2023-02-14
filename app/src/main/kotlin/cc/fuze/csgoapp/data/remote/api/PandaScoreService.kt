package cc.fuze.csgoapp.data.remote.api

import cc.fuze.csgoapp.domain.Match
import retrofit2.http.GET
import retrofit2.http.Query

interface PandaScoreService {

    @GET("csgo/matches")
    suspend fun getCsGoMatches(
        @Query("future") future: Boolean = true,
        @Query("opponents_filled") withOpponents: Boolean = true
    ): List<Match>
}