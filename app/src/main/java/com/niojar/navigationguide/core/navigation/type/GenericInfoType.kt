package com.niojar.navigationguide.core.navigation.type

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T : Parcelable> createNavType(): NavType<T> {
    return object : NavType<T>(isNullableAllowed = true) {
        @SuppressLint("NewApi")
        override fun get(bundle: Bundle, key: String): T? {
            return bundle.getParcelable(key, T::class.java)
        }

        override fun parseValue(value: String): T {
            return Json.decodeFromString<T>(value)
        }

        override fun serializeAsValue(value: T): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value)
        }
    }
}