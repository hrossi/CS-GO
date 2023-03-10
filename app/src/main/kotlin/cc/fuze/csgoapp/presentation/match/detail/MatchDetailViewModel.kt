package cc.fuze.csgoapp.presentation.match.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.fuze.csgoapp.data.MatchRepository
import cc.fuze.csgoapp.data.TeamRepository
import cc.fuze.csgoapp.domain.Match
import cc.fuze.csgoapp.domain.Player
import cc.fuze.csgoapp.presentation.match.list.MatchListState
import kotlinx.coroutines.launch

class MatchDetailViewModel(
    private val teamRepository: TeamRepository
) : ViewModel() {

    private val _matchDetailsState = MutableLiveData<MatchDetailState>()
    val matchDetailsLiveData: LiveData<MatchDetailState> = _matchDetailsState

    fun init(match: Match) {
        viewModelScope.launch {
            runCatching {
                _matchDetailsState.value = getMatchDetails(match)
            }.onFailure {
                _matchDetailsState.value = MatchDetailState.Error
            }
        }
    }

    private suspend fun getMatchDetails(match: Match): MatchDetailState.Success {
        val ids = match.opponents?.map {
            it.opponent.id
        }

        val teams = teamRepository.getTeams(*ids!!.toTypedArray())

        val t1 = teams[0]
        val t2 = teams[1]

        val n = minOf(t1.players.size, t2.players.size)

        val players = mutableListOf<Pair<Player, Player>>()

        for (i in 0 until n) {
            players.add(Pair(t1.players[i], t2.players[i]))
        }

        return MatchDetailState.Success(match, players)
    }
}

sealed class MatchDetailState {
    object Loading : MatchDetailState()

    data class Success(
        val match: Match,
        val players: List<Pair<Player, Player>>
    ) : MatchDetailState()

    object Error : MatchDetailState()
}