package cz.majksa.mailu.client.api.dto

data class DomainDto(
    val name: String,
    val maxUsers: Int,
    val maxAliases: Int,
    val maxAllocatedStorage: Long,
    val signupEnabled: Boolean
)

data class CreateDomain(
    val name: String
)