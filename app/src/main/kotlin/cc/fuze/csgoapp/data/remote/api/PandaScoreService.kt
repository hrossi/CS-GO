package cc.fuze.csgoapp.data.remote.api

import cc.fuze.csgoapp.domain.Match
import cc.fuze.csgoapp.domain.Team
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PandaScoreService {

    @GET("csgo/matches/upcoming")
    suspend fun getCsGoMatches(
        @Query("sort") sort: String = "begin_at",
        @Query("opponents_filled") withOpponents: Boolean = true
    ): List<Match>

    @GET("csgo/teams")
    suspend fun getCsGoTeams(@Query("filter[id]") teamIds: String): List<Team>
}