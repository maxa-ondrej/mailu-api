package cz.majksa.mailu.client.api

import cz.majksa.mailu.client.api.dto.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Client {
    @POST("login")
    fun login(@Body body: LoginUser): Call<AuthToken>

    @GET("domain")
    fun listDomains(@Header("Authorization") authorization: String): Call<List<DomainDto>>

    @POST("domain")
    fun createDomain(@Header("Authorization") authorization: String, @Body body: CreateDomain): Call<DomainDto>

    @GET("domain/{domain}")
    fun getDomain(@Header("Authorization") authorization: String, @Path("domain") domain: String): Call<DomainDto>

    @DELETE("domain/{domain}")
    fun deleteDomain(
        @Header("Authorization") authorization: String,
        @Path("domain") domain: String
    ): Call<SingleResult<Boolean>>

    @POST("domain/{domain}/rename")
    fun renameDomain(
        @Header("Authorization") authorization: String,
        @Path("domain") domain: String,
        @Body body: RenameBody
    ): Call<SingleResult<String>>

    @GET("domain/{domain}/user")
    fun listDomainUsers(@Header("Authorization") authorization: String, @Path("domain") domain: String): Call<List<UserDto>>

    @POST("domain/{domain}/user")
    fun createUser(
        @Header("Authorization") authorization: String,
        @Path("domain") domain: String,
        @Body body: CreateUser
    ): Call<UserDto>

    @GET("domain/{domain}/user/{user}")
    fun getUser(
        @Header("Authorization") authorization: String,
        @Path("domain") domain: String,
        @Path("user") user: String
    ): Call<UserDto>

    @DELETE("domain/{domain}/user/{user}")
    fun deleteUser(
        @Header("Authorization") authorization: String,
        @Path("domain") domain: String,
        @Path("user") user: String
    ): Call<SingleResult<Boolean>>

    @POST("domain/{domain}/user/{user}/rename")
    fun renameUser(
        @Header("Authorization") authorization: String,
        @Path("domain") domain: String,
        @Path("user") user: String,
        @Body body: RenameBody
    ): Call<SingleResult<String>>

    @POST("domain/{domain}/user/{user}/password")
    fun changePassword(
        @Header("Authorization") authorization: String,
        @Path("domain") domain: String,
        @Path("user") user: String,
        @Body body: ChangeUserPassword
    ): Call<SingleResult<Boolean>>

    @GET("domain/{domain}/user/{user}/alias")
    fun listUserAliases(
        @Header("Authorization") authorization: String,
        @Path("domain") domain: String,
        @Path("user") user: String
    ): Call<List<AliasDto>>

    @GET("domain/{domain}/user/{user}/alias")
    fun getAlias(
        @Header("Authorization") authorization: String,
        @Path("domain") domain: String,
        @Path("user") user: String,
        @Path("alias") alias: String
    ): Call<AliasDto>

    @POST("domain/{domain}/user/{user}/alias")
    fun createAlias(
        @Header("Authorization") authorization: String,
        @Path("domain") domain: String,
        @Path("user") user: String,
        @Body body: CreateAlias
    ): Call<AliasDto>

    @POST("domain/{domain}/user/{user}/alias/{alias}/rename")
    fun renameAlias(
        @Header("Authorization") authorization: String,
        @Path("domain") domain: String,
        @Path("user") user: String,
        @Path("alias") alias: String,
        @Body body: RenameBody
    ): Call<SingleResult<String>>

    @DELETE("domain/{domain}/user/{user}/alias/{alias}")
    fun deleteAlias(
        @Header("Authorization") authorization: String,
        @Path("domain") domain: String,
        @Path("user") user: String,
        @Path("alias") alias: String
    ): Call<SingleResult<Boolean>>
}

fun createClient(baseUrl: String): Client = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(Client::class.java)