package fr.isen.ravel.androiderestaurant

import java.io.Serializable

data class CartItem(val item: Items, val quantity: Int) : Serializable
