package id.fadillah.fundamentalsubmission.util.helper

import android.content.ContentValues
import id.fadillah.fundamentalsubmission.data.source.local.model.UserDatabaseEntity

object DatabaseHelper {
//    Column Name
    private const val COLUMN_USERNAME = "username"
    private const val COLUMN_NAME = "name"
    private const val COLUMN_IMAGE = "image"
    private const val COLUMN_COMPANY = "company"
    private const val COLUMN_LOCATION = "location"
    private const val COLUMN_REPOSITORY = "repository"
    private const val COLUMN_FOLLOWERS = "followers"
    private const val COLUMN_FOLLOWING = "following"
    private const val COLUMN_FOLLOWERS_URL = "followersUrl"
    private const val COLUMN_FOLLOWING_URL = "followingUrl"
    private const val COLUMN_REPOS_URL = "reposUrl"
    private const val COLUMN_CREATE_AT = "createAt"
    private const val COLUMN_TYPE = "type"
    private const val COLUMN_BIO = "bio"
    private const val COLUMN_BOOKMARKED = "bookmarked"

    fun fromContentValues(values: ContentValues?): UserDatabaseEntity {
        return UserDatabaseEntity(username = "-", bookmarked = false).apply {
            if (values != null) {
                if (values.containsKey(COLUMN_USERNAME)) {
                    username = values.getAsString(COLUMN_USERNAME)
                }
                if (values.containsKey(COLUMN_NAME)) {
                    name = values.getAsString(COLUMN_NAME)
                }
                if (values.containsKey(COLUMN_IMAGE)) {
                    image = values.getAsString(COLUMN_IMAGE)
                }
                if (values.containsKey(COLUMN_COMPANY)) {
                    company = values.getAsString(COLUMN_COMPANY)
                }
                if (values.containsKey(COLUMN_LOCATION)) {
                    location = values.getAsString(COLUMN_LOCATION)
                }
                if (values.containsKey(COLUMN_REPOSITORY)) {
                    repository = values.getAsInteger(COLUMN_REPOSITORY)
                }
                if (values.containsKey(COLUMN_FOLLOWERS)) {
                    followers = values.getAsInteger(COLUMN_FOLLOWERS)
                }
                if (values.containsKey(COLUMN_FOLLOWING)) {
                    following = values.getAsInteger(COLUMN_FOLLOWING)
                }
                if (values.containsKey(COLUMN_FOLLOWERS_URL)) {
                    followersUrl = values.getAsString(COLUMN_FOLLOWERS_URL)
                }
                if (values.containsKey(COLUMN_FOLLOWING_URL)) {
                    followingUrl = values.getAsString(COLUMN_FOLLOWING_URL)
                }
                if (values.containsKey(COLUMN_REPOS_URL)) {
                    reposUrl = values.getAsString(COLUMN_REPOS_URL)
                }
                if (values.containsKey(COLUMN_CREATE_AT)) {
                    createAt = values.getAsString(COLUMN_CREATE_AT)
                }
                if (values.containsKey(COLUMN_TYPE)) {
                    type = values.getAsString(COLUMN_TYPE)
                }
                if (values.containsKey(COLUMN_BIO)) {
                    bio = values.getAsString(COLUMN_BIO)
                }
                if (values.containsKey(COLUMN_BOOKMARKED)) {
                    bookmarked = values.getAsBoolean(COLUMN_BOOKMARKED)
                }
            }
        }
    }
}