package cc.fuze.csgoapp.data

import cc.fuze.csgoapp.data.remote.api.PandaScoreService
import cc.fuze.csgoapp.domain.Match

class MatchRepository(private val service: PandaScoreService) {

    suspend fun getCsGoMatches(): List<Match> {
        return service.getCsGoMatches()
    }
}