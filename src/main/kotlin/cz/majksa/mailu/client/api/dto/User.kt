package cz.majksa.mailu.client.api.dto

data class UserDto(
    val email: String,
    val displayName: String,
    val name: String,
    val domain: String,
    val enabled: Boolean,
    val storage: Storage,
    val forward: ForwardRule?,
    val replyRule: ReplyRule?,
    val settings: Settings
)

data class LoginUser(
    val username: String,
    val password: String
)

data class AuthToken(
    val token: String,
)

data class CreateUser(
    val name: String,
    val displayName: String,
    val password: String
)

data class ChangeUserPassword(
    val password: String
)

data class SetAllocated(val allocated: Long)

data class Storage(
    val allocated: Long,
    val used: Long,
    val remaining: Long,
)

data class Settings(
    val admin: Boolean,
    val imap: Boolean,
    val pop: Boolean,
    val spam: Boolean,
    val spamThreshold: Int,
)

data class ForwardRule(
    val keep: Boolean,
    val destination: String
)

data class ReplyRule(
    val subject: String?,
    val body: String?,
    val start: String,
    val end: String
)