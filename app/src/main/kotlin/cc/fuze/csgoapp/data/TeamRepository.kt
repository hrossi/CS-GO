package cc.fuze.csgoapp.data

import cc.fuze.csgoapp.data.remote.api.PandaScoreService
import cc.fuze.csgoapp.domain.Team

class TeamRepository(private val service: PandaScoreService) {

    suspend fun getTeams(vararg teamIds: String): List<Team> {
        return service.getCsGoTeams(teamIds.joinToString(","))
    }
}