package id.fadillah.fundamentalsubmission.data.source.local

import androidx.lifecycle.LiveData
import id.fadillah.fundamentalsubmission.data.source.local.model.UserDatabaseEntity
import id.fadillah.fundamentalsubmission.data.source.local.room.UserDao

class LocalDataSource(private val userDao: UserDao) {

    fun loadAllUser(): LiveData<List<UserDatabaseEntity>> = userDao.loadAllUser()
    fun searchUser(username: String): LiveData<List<UserDatabaseEntity>> = userDao.loadSearchUser("%$username%")
    fun checkIsFavorite(username: String): LiveData<List<UserDatabaseEntity>> = userDao.loadSearchUser("%$username%")
    suspend fun insertUser(userDatabaseEntity: UserDatabaseEntity) = userDao.addUser(userDatabaseEntity)
    suspend fun deleteUser(userDatabaseEntity: UserDatabaseEntity) = userDao.deleteUser(userDatabaseEntity)
}