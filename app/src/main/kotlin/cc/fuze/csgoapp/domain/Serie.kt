package cc.fuze.csgoapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Serie(
    val id: String,
    val name: String,
) : Parcelable