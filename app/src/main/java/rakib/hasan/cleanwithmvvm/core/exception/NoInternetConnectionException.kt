package rakib.hasan.cleanwithmvvm.core.exception

import okio.IOException

class NoInternetConnectionException : IOException() {
    override val message : String
        get() = "You're offline!"
}