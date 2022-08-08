package cz.majksa.mailu.client

import com.google.gson.Gson
import cz.majksa.mailu.client.api.Client
import cz.majksa.mailu.client.api.createClient
import cz.majksa.mailu.client.api.dto.*
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.CompletableFuture

fun createMailu(username: String, password: String, baseUrl: String = "http://mailu_api_1"): CompletableFuture<Mailu> {
    val client = createClient(baseUrl)
    return client.login(LoginUser(username, password))
        .handle {
            token
        }.thenApply {
            Mailu("Bearer $it", client)
        }
}

class Mailu internal constructor(val authorization: String, val client: Client) {

    fun listDomains(): Result<List<Domain>> = client.listDomains(authorization).handle {
        map {
            Domain(this@Mailu, it)
        }
    }

    fun getDomain(name: String): Result<Domain> = client.getDomain(authorization, name).handle {
        Domain(this@Mailu, this)
    }

    fun createDomain(name: String): Result<Domain> = client.createDomain(authorization, CreateDomain(name)).handle {
        Domain(this@Mailu, this)
    }

}

val gson = Gson()

inline fun <reified D, reified E> Call<D>.handle(crossinline mapper: (D.() -> E)): CompletableFuture<E> = CompletableFuture.supplyAsync {
    execute().map(mapper)
}

inline fun <reified D, reified E> Response<D>.map(mapper: (D.() -> E)): E = if (isSuccessful) {
    body()!!.mapper()
} else {
    val dto = gson.fromJson(errorBody()!!.string(), ApiErrorDto::class.java)
    throw ApiError(dto.code, dto.description, dto.message)
}