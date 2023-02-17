package cc.fuze.csgoapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class League(
    val id: String,
    val name: String,
    @SerializedName("image_url")
    val image: String?
) : Parcelable
