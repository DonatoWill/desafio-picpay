package commons.exception

class WalletNotFoundException(message: String, cause: Throwable? = null) : RuntimeException(message, cause) {
}