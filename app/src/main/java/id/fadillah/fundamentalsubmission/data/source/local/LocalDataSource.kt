package id.fadillah.fundamentalsubmission.data.source.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import id.fadillah.fundamentalsubmission.data.source.local.model.UserDatabaseEntity
import id.fadillah.fundamentalsubmission.data.source.local.room.UserDao

class LocalDataSource(private val userDao: UserDao) {
//  For apps
    fun loadAllUser(): LiveData<List<UserDatabaseEntity>> = userDao.loadAllUser()
    fun searchUser(username: String): LiveData<List<UserDatabaseEntity>> = userDao.loadSearchUser("%$username%")
    fun checkIsFavorite(username: String): LiveData<List<UserDatabaseEntity>> = userDao.loadSearchUser("%$username%")
    suspend fun insertUser(userDatabaseEntity: UserDatabaseEntity) = userDao.addUser(userDatabaseEntity)
    suspend fun deleteUser(userDatabaseEntity: UserDatabaseEntity) = userDao.deleteUser(userDatabaseEntity)
    fun loadAllUserForWidget(): List<UserDatabaseEntity> = userDao.loadAllUserForWidget()

//    For Content Provider
    fun insertCursorUser(user: UserDatabaseEntity): Long = userDao.insertUserCursor(user)
    fun loadAllUserCursor(): Cursor? = userDao.loadAllUserCursor()
    fun loadSearchUserCursor(username: String): Cursor? = userDao.loadSearchUserCursor("%$username%")
    fun deleteUserCursorById(username: String): Int = userDao.deleteUserCursorById(username)
}