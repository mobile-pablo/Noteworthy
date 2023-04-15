package com.mobile.pablo.core.data

class DataTransfer<T>(
    val data: T? = null,
    val error: Exception? = null
) {

    val isSuccessful: Boolean = data != null

    suspend fun <R> map(transform: suspend (T) -> R): DataTransfer<R> {
        return DataTransfer(
            data = data?.let {
                transform.invoke(it)
            },
            error = error
        )
    }

    fun doIfSuccessful(action: (T) -> Unit) {
        if (isSuccessful) {
            action.invoke(data!!)
        }
    }
}
