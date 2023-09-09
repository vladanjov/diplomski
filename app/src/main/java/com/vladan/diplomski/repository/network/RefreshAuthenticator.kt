package com.vladan.diplomski.repository.network

import com.vladan.diplomski.repository.Repository
import com.vladan.diplomski.repository.pref.Preferences
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class RefreshAuthenticator : Authenticator {

    lateinit var repo: Repository
    lateinit var prefs: Preferences

    override fun authenticate(route: Route?, response: Response): Request? {
        response.request.header("Authorization")?.let { failedToken ->
            if (failedToken == "Bearer ${prefs.accessToken}") {
                synchronized(this) {
                    if (failedToken == "Bearer ${prefs.accessToken}") {
                        runBlocking {
                            val results = repo.refresh()
                            if (results is Result.Error) prefs.logout()
                        }
                    }
                }
            }
            prefs.accessToken?.let {
                if (failedToken != "Bearer $it") return response.request.newBuilder()
                    .header("Authorization", "Bearer $it").build()
            }
        }
        return null
    }
}