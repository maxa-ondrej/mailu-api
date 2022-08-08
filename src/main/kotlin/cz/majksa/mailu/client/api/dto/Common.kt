package cz.majksa.mailu.client.api.dto

import java.util.concurrent.CompletableFuture

data class SingleResult<T>(val data: T)

data class RenameBody(val name: String)

data class ApiErrorDto(val code: Int, val description: String, val message: String)

class ApiError(val code: Int, val description: String, message: String): Throwable(message)

typealias Result<T> = CompletableFuture<T>