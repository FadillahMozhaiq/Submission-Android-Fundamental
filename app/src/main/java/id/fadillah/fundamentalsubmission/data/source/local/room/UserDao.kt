package id.fadillah.fundamentalsubmission.data.source.local.room

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import id.fadillah.fundamentalsubmission.data.source.local.model.UserDatabaseEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserDatabaseEntity)

    @Query("SELECT * FROM user_table")
    fun loadAllUser(): LiveData<List<UserDatabaseEntity>>

    @Query("SELECT * FROM user_table WHERE username LIKE :username ")
    fun loadSearchUser(username: String): LiveData<List<UserDatabaseEntity>>

    @Delete
    suspend fun deleteUser(user: UserDatabaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserCursor(user: UserDatabaseEntity): Long

    @Query("SELECT * FROM user_table")
    fun loadAllUserCursor(): Cursor?

    @Query("SELECT * FROM user_table WHERE username LIKE :username ")
    fun loadSearchUserCursor(username: String): Cursor?

    @Query("DELETE FROM user_table WHERE username LIKE :username")
    fun deleteUserCursorById(username: String): Int

    @Query("SELECT * FROM user_table")
    fun loadAllUserForWidget(): List<UserDatabaseEntity>
}