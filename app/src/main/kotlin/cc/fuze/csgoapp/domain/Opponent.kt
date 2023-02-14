package cc.fuze.csgoapp.domain

import com.google.gson.annotations.SerializedName

data class Opponent(
    val id: String,
    val name: String,
    @SerializedName("image_url")
    val image: String?
)
