package cc.fuze.csgoapp.domain

import com.google.gson.annotations.SerializedName

data class League(
    val id: String,
    val name: String,
    @SerializedName("image_url")
    val image: String?
)
