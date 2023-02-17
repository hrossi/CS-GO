package cc.fuze.csgoapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team (
    val id: String,
    val players: List<Player>
) : Parcelable