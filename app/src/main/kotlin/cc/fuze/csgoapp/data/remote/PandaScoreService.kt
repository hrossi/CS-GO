package cc.fuze.csgoapp.data.remote

import cc.fuze.csgoapp.domain.Match
import retrofit2.http.GET

interface PandaScoreService {

    @GET("csgo/matches")
    suspend fun getCsGoMatches(): List<Match>
}