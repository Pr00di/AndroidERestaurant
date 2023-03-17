package fr.isen.ravel.androiderestaurant

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
data class MenuResult(val data: List<Category>)
data class Category(val name_fr: String, val items: List<MenuItem>)
data class MenuItem(val name_fr: String, val description_fr: String, val price: String, val images: List<String>)
