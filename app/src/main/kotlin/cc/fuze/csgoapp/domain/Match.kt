package cc.fuze.csgoapp.domain

data class Match(
    val id: String,
    val name: String,
    val league: League,
    val opponents: List<Opponents>?
)