package cz.majksa.mailu.client

import cz.majksa.mailu.client.api.dto.*

class Alias(private val api: Mailu, dto: AliasDto) {
    var name = dto.localPart
        private set
    val email = dto.email
    val domainName = dto.domainName
    val destination = dto.destination
    val wildcard = dto.wildcard
    val userName = destination.removeSuffix("@$domainName")

    val domain: Result<Domain>
        get() = api.getDomain(domainName)

    val user: Result<User>
        get() = api.getDomain(domainName).join().getUser(userName)

    fun rename(name: String): Result<String> = api.client.renameAlias(api.authorization, domainName, userName, this.name, RenameBody(name)).handle {
        data
    }.thenApply {
        this.name = name
        it
    }

    fun delete(): Result<Boolean> = api.client.deleteAlias(api.authorization, domainName, userName, this.name).handle {
        data
    }

    override fun toString(): String {
        return "Alias($email->$destination)"
    }


}