package id.fadillah.fundamentalsubmission.util

import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.data.source.network.response.DetailUserResponse
import id.fadillah.fundamentalsubmission.data.source.network.response.ItemsUserResponse
import id.fadillah.fundamentalsubmission.data.source.network.response.SearchUserResponse

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
}