package com.niojar.navigationguide.core.navigation.type

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.niojar.navigationguide.core.navigation.SettingsInfo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// Settings Info Type Example, not used in this example
// Use the createNavType function to create a custom type
val settingsInfoType = object : NavType<SettingsInfo>(isNullableAllowed = true) {
    @SuppressLint("NewApi")
    override fun get(bundle: Bundle, key: String): SettingsInfo? {
        return bundle.getParcelable(key, SettingsInfo::class.java)
    }

    override fun parseValue(value: String): SettingsInfo {
        return Json.decodeFromString<SettingsInfo>(value)
    }

    override fun serializeAsValue(value: SettingsInfo): String {
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: SettingsInfo) {
        bundle.putParcelable(key, value)
    }

}