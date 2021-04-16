package id.fadillah.userconsumerapp.util.helper

import android.net.Uri

object DatabaseHelper {
//    Column Name
    const val COLUMN_USERNAME = "username"
    const val COLUMN_NAME = "name"
    const val COLUMN_IMAGE = "image"
    const val COLUMN_COMPANY = "company"
    const val COLUMN_LOCATION = "location"
    const val COLUMN_REPOSITORY = "repository"
    const val COLUMN_FOLLOWERS = "followers"
    const val COLUMN_FOLLOWING = "following"
    const val COLUMN_FOLLOWERS_URL = "followersUrl"
    const val COLUMN_FOLLOWING_URL = "followingUrl"
    const val COLUMN_REPOS_URL = "reposUrl"
    const val COLUMN_CREATE_AT = "createAt"
    const val COLUMN_TYPE = "type"
    const val COLUMN_BIO = "bio"
    const val COLUMN_BOOKMARKED = "bookmarked"

    private const val TABLE_NAME = "user_table"
    private const val AUTHORITY = "id.fadillah.fundamentalsubmission"
    private const val SCHEME = "content"

    // untuk membuat URI content://id.fadillah.fundamentalsubmission/user_table
    val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
        .authority(AUTHORITY)
        .appendPath(TABLE_NAME)
        .build()
}