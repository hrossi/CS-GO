package cc.fuze.csgoapp.presentation.match.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.fuze.csgoapp.data.MatchRepository
import cc.fuze.csgoapp.domain.Match
import kotlinx.coroutines.launch

class MatchListViewModel(
    private val repository: MatchRepository
) : ViewModel() {

    private val _matchListStateLiveData = MutableLiveData<MatchListState>()
    val matchListStateLiveData: LiveData<MatchListState> = _matchListStateLiveData

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _matchListStateLiveData.value = MatchListState.Loading
            runCatching {
                _matchListStateLiveData.value = MatchListState.Success(repository.getCsGoMatches())
            }.onFailure {
                _matchListStateLiveData.value = MatchListState.Error
            }
        }
    }
}

sealed class MatchListState {
    object Loading : MatchListState()

    data class Success(
        val matches: List<Match>
    ) : MatchListState()

    object Error : MatchListState()
}