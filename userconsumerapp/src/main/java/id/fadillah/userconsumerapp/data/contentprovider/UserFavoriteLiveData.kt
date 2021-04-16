package id.fadillah.userconsumerapp.data.contentprovider

import android.content.Context
import android.database.Cursor
import android.net.Uri
import id.fadillah.userconsumerapp.data.source.local.model.UserDatabaseEntity
import id.fadillah.userconsumerapp.util.helper.DataMapper
import id.fadillah.userconsumerapp.util.helper.DatabaseHelper.CONTENT_URI

class UserFavoriteLiveData(
    private val context: Context,
    private val uri: Uri,
) : ContentProviderLiveData<List<UserDatabaseEntity>>(context, CONTENT_URI) {

    private fun loadUser(): Cursor? = context.contentResolver.query(
        uri,
        null,
        null,
        null,
        null
    )

    override fun getContentProviderValue(): List<UserDatabaseEntity> = DataMapper.mapCursorToList(loadUser())
}