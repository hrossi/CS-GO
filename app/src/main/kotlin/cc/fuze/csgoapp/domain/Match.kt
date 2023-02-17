package cc.fuze.csgoapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class Match(
    val id: String,
    val name: String,
    val league: League,
    val serie: Serie,
    val opponents: List<Opponents>?,
    @SerializedName("begin_at")
    val date: Date?
) : Parcelable