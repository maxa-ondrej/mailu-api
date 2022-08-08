package cz.majksa.mailu.client.api.dto

data class AliasDto(
    val email: String,
    val destination: String,
    val localPart: String,
    val domainName: String,
    val wildcard: Boolean
)

data class CreateAlias(
    val name: String,
    val wildcard: Boolean
)