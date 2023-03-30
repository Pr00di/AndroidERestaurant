package fr.isen.ravel.androiderestaurant

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken

inline fun <reified T> String.fromJson(): T? {
    return try {
        Gson().fromJson(this, object : TypeToken<T>() {}.type)
    } catch (e: JsonSyntaxException) {
        null
    }
}

fun <T> T.toJson(): String {
    return Gson().toJson(this)
}
