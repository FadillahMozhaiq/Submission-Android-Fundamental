package id.fadillah.fundamentalsubmission.util

import id.fadillah.fundamentalsubmission.data.model.RepositoryEntity
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.data.source.network.response.*
import java.text.SimpleDateFormat

object DataMapper {
    fun listUserResponseToEntity(input: List<ItemsUserResponse>): List<UserEntity> =
        input.map {
            UserEntity(
                it.login,
                it.login,
                it.avatar_url,
                type = it.type
            )
        }

    fun listFollowersResponseToEntity(input: List<ItemFollowerResponse>): List<UserEntity> =
        input.map {
            UserEntity(
                it.login,
                it.login,
                it.avatarUrl,
                type = it.type
            )
        }

    fun listFollowingResponseToEntity(input: List<ItemFollowingResponse>): List<UserEntity> =
        input.map {
            UserEntity(
                it.login,
                it.login,
                it.avatarUrl,
                type = it.type
            )
        }

    fun listSearchUserResponseToEntity(input: SearchUserResponse): List<UserEntity> =
        input.items.map {
            UserEntity(
                it.login,
                it.login,
                it.avatarUrl,
                type = it.type
            )
        }

    fun detailUserResponseToEntity(input: DetailUserResponse): UserEntity = UserEntity(
        input.login,
        input.name,
        input.avatarUrl,
        input.company,
        input.location,
        input.publicRepos,
        input.followers,
        input.following,
        input.followersUrl,
        input.followingUrl,
        input.reposUrl,
        input.createdAt,
        input.type,
        input.bio
    )

    fun repositoryResponseToListEntity(input: List<ItemRepositoryResponse>) =
        input.map {
            RepositoryEntity(
                it.id ?: 0,
                it.name ?: "Unknown",
                it.description,
                convertIsoTimeToDate(it.createdAt),
                it.language,
                it.stargazersCount ?: 0
            )
        }

    private fun convertIsoTimeToDate(time: String?): String {
        time ?: return ""
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        return formatter.format(parser.parse(time))
    }
}