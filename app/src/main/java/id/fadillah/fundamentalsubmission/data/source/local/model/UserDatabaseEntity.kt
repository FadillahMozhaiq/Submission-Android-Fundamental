package id.fadillah.fundamentalsubmission.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDatabaseEntity(
    @PrimaryKey
    val username: String,
    val name: String? = null,
    val image: String? = null,
    val company: String? = null,
    val location: String? = null,
    val repository: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    val followersUrl: String? = null,
    val followingUrl: String? = null,
    val reposUrl: String? = null,
    val createAt: String? = null,
    val type: String? = null,
    val bio: String? = null,
    var bookmarked: Boolean
)