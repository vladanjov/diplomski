package com.vladan.diplomski.repository.pref

import kotlinx.coroutines.flow.Flow
import splitties.preferences.Preferences

object Preferences : Preferences("diplomski") {

    val accessTokenFlow: Flow<String?>
    var accessToken by stringOrNullPref("access_token", null).also {
        accessTokenFlow = it.valueFlow()
    }
    var refreshToken by stringOrNullPref("refresh_token", null)

    fun logout() {
        accessToken = null
        refreshToken = null
    }

}
