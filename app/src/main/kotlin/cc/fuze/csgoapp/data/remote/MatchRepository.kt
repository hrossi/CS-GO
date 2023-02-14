package cc.fuze.csgoapp.data.remote

import cc.fuze.csgoapp.domain.Match

class MatchRepository(private val service: PandaScoreService) {

    suspend fun getCsGoMatches(): List<Match> {
        return service.getCsGoMatches()
    }
}