package com.datasoft.kotlintemplate.core.exception

import okio.IOException

class NoInternetConnectionException : IOException() {
    override val message : String
        get() = "You're offline!"
}