package fr.isen.ravel.androiderestaurant

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Detail_item(
    val id: Int,
    val nom: String,
    val description: String,
    val prix: Double
) : Parcelable