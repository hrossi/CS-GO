package cc.fuze.csgoapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Opponents(
    val opponent: Opponent,
    val type: String
) : Parcelable
