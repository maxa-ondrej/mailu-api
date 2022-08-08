package cz.majksa.mailu.client

import cz.majksa.mailu.client.api.dto.CreateUser
import cz.majksa.mailu.client.api.dto.RenameBody
import cz.majksa.mailu.client.api.dto.Result
import java.util.concurrent.CompletableFuture
import cz.majksa.mailu.client.api.dto.DomainDto as DomainDto

class Domain(private val api: Mailu, dto: DomainDto) {
    var name = dto.name
        private set

    val maxUsers = dto.maxUsers
    val maxAliases = dto.maxAliases
    val maxAllocatedStorage = dto.maxAllocatedStorage
    val signupEnabled = dto.signupEnabled

    fun rename(name: String): Result<String> = api.client.renameDomain(api.authorization, this.name, RenameBody(name)).handle {
        data
    }.thenApply {
        this.name = name
        it
    }

    fun delete(): Result<Boolean> = api.client.deleteDomain(api.authorization, this.name).handle {
        data
    }

    fun listUsers(): Result<List<User>> = api.client.listDomainUsers(api.authorization, this.name).handle {
        map {
            User(api, it)
        }
    }

    fun getUser(name: String): CompletableFuture<User> = api.client.getUser(api.authorization, this.name, name).handle {
        User(api, this)
    }

    fun createUser(name: String, displayedName: String, password: String): Result<User> =
        api.client.createUser(api.authorization, this.name, CreateUser(name, displayedName, password)).handle {
            User(api, this)
        }

    override fun toString(): String {
        return "Domain($name)"
    }


}