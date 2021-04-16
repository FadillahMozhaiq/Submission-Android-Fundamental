package id.fadillah.userconsumerapp.util.helper

import android.content.ContentValues
import android.database.Cursor
import androidx.core.content.contentValuesOf
import id.fadillah.userconsumerapp.data.model.RepositoryEntity
import id.fadillah.userconsumerapp.data.model.UserEntity
import id.fadillah.userconsumerapp.data.source.local.model.UserDatabaseEntity
import id.fadillah.userconsumerapp.data.source.network.response.*
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_BIO
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_BOOKMARKED
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_COMPANY
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_CREATE_AT
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_FOLLOWERS
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_FOLLOWERS_URL
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_FOLLOWING
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_FOLLOWING_URL
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_IMAGE
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_LOCATION
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_NAME
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_REPOSITORY
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_REPOS_URL
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_TYPE
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.COLUMN_USERNAME
import java.text.SimpleDateFormat
import java.util.*

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
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.US)
        return formatter.format(parser.parse(time))
    }

    fun listUserDatabaseToUserEntity(input: List<UserDatabaseEntity>): List<UserEntity> =
        input.map {
            UserEntity(
                it.username,
                it.name,
                it.image,
                it.company,
                it.location,
                it.repository,
                it.followers,
                it.following,
                it.followersUrl,
                it.followingUrl,
                it.reposUrl,
                it.createAt,
                it.type,
                it.bio,
                it.bookmarked
            )
        }

    fun mapCursorToList(userCursor: Cursor?): List<UserDatabaseEntity> {
        val users = ArrayList<UserDatabaseEntity>()
        userCursor?.apply {
            while (moveToNext()) {
                users.add(UserDatabaseEntity(
                    username = getString(getColumnIndexOrThrow(COLUMN_USERNAME)),
                    name = getString(getColumnIndexOrThrow(COLUMN_NAME)),
                    image = getString(getColumnIndexOrThrow(COLUMN_IMAGE)),
                    company = getString(getColumnIndexOrThrow(COLUMN_COMPANY)),
                    location = getString(getColumnIndexOrThrow(COLUMN_LOCATION)),
                    repository = getInt(getColumnIndexOrThrow(COLUMN_REPOSITORY)),
                    followers = getInt(getColumnIndexOrThrow(COLUMN_FOLLOWERS)),
                    following = getInt(getColumnIndexOrThrow(COLUMN_FOLLOWING)),
                    followersUrl = getString(getColumnIndexOrThrow(COLUMN_FOLLOWERS_URL)),
                    followingUrl = getString(getColumnIndexOrThrow(COLUMN_FOLLOWING_URL)),
                    reposUrl = getString(getColumnIndexOrThrow(COLUMN_REPOS_URL)),
                    createAt = getString(getColumnIndexOrThrow(COLUMN_CREATE_AT)),
                    type = getString(getColumnIndexOrThrow(COLUMN_TYPE)),
                    bio = getString(getColumnIndexOrThrow(COLUMN_BIO)),
                    bookmarked = getInt(getColumnIndexOrThrow(COLUMN_BOOKMARKED)) > 0
                ))
            }
        }
        return users
    }

    fun userEntityToContentValues(user: UserEntity): ContentValues =
        contentValuesOf(
            COLUMN_USERNAME to user.username,
            COLUMN_NAME to user.name,
            COLUMN_IMAGE to user.image,
            COLUMN_COMPANY to user.company,
            COLUMN_LOCATION to user.location,
            COLUMN_REPOSITORY to user.repository,
            COLUMN_FOLLOWERS to user.followers,
            COLUMN_FOLLOWING to user.following,
            COLUMN_FOLLOWERS_URL to user.followersUrl,
            COLUMN_FOLLOWING_URL to user.followingUrl,
            COLUMN_REPOS_URL to user.reposUrl,
            COLUMN_CREATE_AT to user.createAt,
            COLUMN_TYPE to user.type,
            COLUMN_BIO to user.bio,
            COLUMN_BOOKMARKED to user.bookmarked
        )
}