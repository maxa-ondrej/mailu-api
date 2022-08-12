package cz.majksa.mailu.client

import cz.majksa.mailu.client.api.dto.*

class User(private val api: Mailu, dto: UserDto) {
    var name = dto.email
        private set
    val email = dto.email
    val domainName = dto.domain
    val displayName = dto.displayName
    val enabled = dto.enabled
    val storage = dto.storage
    val forwardRule = dto.forward
    val replyRule = dto.replyRule
    val settings = dto.settings

    val domain: Result<Domain>
        get() = api.getDomain(domainName)

    fun rename(name: String): Result<String> =
        api.client.renameUser(api.authorization, domainName, this.name, RenameBody(name)).handle {
            data
        }.thenApply {
            this.name = name
            it
        }

    fun delete(): Result<Boolean> = api.client.deleteUser(api.authorization, domainName, this.name).handle {
        data
    }

    fun getAlias(name: String): Result<Alias> = api.client.getAlias(api.authorization, domainName, this.name, name).handle {
        Alias(api, this)
    }

    fun createAlias(name: String, wildcard: Boolean = false): Result<Alias> =
        api.client.createAlias(api.authorization, domainName, this.name, CreateAlias(name, wildcard)).handle {
            Alias(api, this)
        }

    fun setAllocatedStorage(storage: Long) =
        api.client.allocateUserStorage(api.authorization, domainName, this.name, SetAllocated(storage)).handle {
            data
        }

    fun changePassword(password: String) =
        api.client.changePassword(api.authorization, domainName, this.name, ChangeUserPassword(password)).handle {
            data
        }

    override fun toString(): String {
        return "User($email)"
    }


}