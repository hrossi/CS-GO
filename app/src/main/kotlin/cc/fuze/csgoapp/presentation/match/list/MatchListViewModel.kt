package cc.fuze.csgoapp.presentation.match.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.fuze.csgoapp.data.MatchRepository
import cc.fuze.csgoapp.domain.Match
import kotlinx.coroutines.launch
import java.util.*

class MatchListViewModel(
    private val repository: MatchRepository
) : ViewModel() {

    private val _matchesLiveData = MutableLiveData<List<Match>>()
    val matchesLiveData: LiveData<List<Match>> = _matchesLiveData

    fun refresh() {
        viewModelScope.launch {
            _matchesLiveData.value = repository.getCsGoMatches().filter {
                it.date != null && it.date.after(Date())
            }.sortedBy {
                it.date
            }
        }
    }
}