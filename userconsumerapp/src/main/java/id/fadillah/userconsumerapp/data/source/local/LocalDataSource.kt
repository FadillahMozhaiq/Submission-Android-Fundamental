package id.fadillah.userconsumerapp.data.source.local

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import id.fadillah.userconsumerapp.data.contentprovider.UserFavoriteLiveData
import id.fadillah.userconsumerapp.data.source.local.model.UserDatabaseEntity
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.CONTENT_URI

class LocalDataSource(private val context: Context) {
    //  For apps
    private val contentResolver: ContentResolver = context.contentResolver

    fun loadAllUser(): LiveData<List<UserDatabaseEntity>> =
        UserFavoriteLiveData(context, CONTENT_URI)

    fun searchUser(username: String): LiveData<List<UserDatabaseEntity>> =
        UserFavoriteLiveData(context, Uri.parse("$CONTENT_URI/$username"))

    fun checkIsFavorite(username: String): LiveData<List<UserDatabaseEntity>> =
        UserFavoriteLiveData(context, Uri.parse("$CONTENT_URI//$username"))

    fun insertUser(values: ContentValues) =
        contentResolver.insert(CONTENT_URI, values)

    fun deleteUser(username: String): Int =
        contentResolver.delete(Uri.parse("$CONTENT_URI/$username"), null, null)
}