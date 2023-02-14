package cc.fuze.csgoapp.presentation.match.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.fuze.csgoapp.data.remote.MatchRepository
import cc.fuze.csgoapp.domain.Match
import kotlinx.coroutines.launch

class MatchListViewModel(
    private val repository: MatchRepository
) : ViewModel() {

    val matchesLiveData = MutableLiveData<List<Match>>()

    fun init() {
        viewModelScope.launch {
            matchesLiveData.value = repository.getCsGoMatches()
        }
    }
}