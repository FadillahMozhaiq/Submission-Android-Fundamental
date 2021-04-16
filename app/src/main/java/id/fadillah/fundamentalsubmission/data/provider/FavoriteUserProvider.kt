package id.fadillah.fundamentalsubmission.data.provider

import android.content.*
import android.database.Cursor
import android.net.Uri
import android.util.Log
import id.fadillah.fundamentalsubmission.data.source.local.LocalDataSource
import id.fadillah.fundamentalsubmission.util.helper.DatabaseHelper.fromContentValues
import id.fadillah.fundamentalsubmission.util.injection.Injection

class FavoriteUserProvider : ContentProvider() {

    companion object {
        private const val TAG = "FavoriteUserProvider"
        private const val TABLE_NAME = "user_table"
        private const val AUTHORITY = "id.fadillah.fundamentalsubmission"
        private const val USER = 1
        private const val USER_NAME = 2
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var localDataSource: LocalDataSource
    }

    init {
        // content://id.fadillah.fundamentalsubmission/user_table
        uriMatcher.addURI(AUTHORITY, TABLE_NAME, USER)

        // content://id.fadillah.fundamentalsubmission/user_table/username
        uriMatcher.addURI(AUTHORITY, "$TABLE_NAME/*", USER_NAME)
    }

    override fun onCreate(): Boolean {
        localDataSource = Injection.provideLocalDataSource(context as Context)
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return when (uriMatcher.match(uri)) {
            USER -> {
                val context = context ?: return null
                val added = localDataSource.insertCursorUser(fromContentValues(values))
                context.contentResolver.notifyChange(uri, null)
                ContentUris.withAppendedId(uri, added)
            }
            USER_NAME -> throw IllegalArgumentException("Invalid URI, cannot insert with ID: $uri")
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor: Cursor? = when (uriMatcher.match(uri)) {
            USER -> localDataSource.loadAllUserCursor()
            USER_NAME -> localDataSource.loadSearchUserCursor(uri.lastPathSegment.toString())
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
        cursor?.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        Log.d(TAG, "delete: ${uri.lastPathSegment.toString()}")
        return when (uriMatcher.match(uri)) {
            USER -> throw IllegalArgumentException("Invalid URI, cannot delete without USERNAME: $uri")
            USER_NAME -> {
                val context = context ?: return 0
                Log.d(TAG, "delete: ${uri.lastPathSegment.toString()}")
                val count = localDataSource.deleteUserCursorById(uri.lastPathSegment.toString())
                context.contentResolver.notifyChange(uri, null)
                count
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? = null
}