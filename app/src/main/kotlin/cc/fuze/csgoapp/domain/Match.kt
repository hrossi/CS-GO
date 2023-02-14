package cc.fuze.csgoapp.domain

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Match(
    val id: String,
    val name: String,
    val league: League,
    val serie: Serie,
    val opponents: List<Opponents>?,
    @SerializedName("begin_at")
    val date: Date
)