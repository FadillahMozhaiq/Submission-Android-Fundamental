package id.fadillah.fundamentalsubmission.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDatabaseEntity(
    @PrimaryKey
    var username: String,
    var name: String? = null,
    var image: String? = null,
    var company: String? = null,
    var location: String? = null,
    var repository: Int = 0,
    var followers: Int = 0,
    var following: Int = 0,
    var followersUrl: String? = null,
    var followingUrl: String? = null,
    var reposUrl: String? = null,
    var createAt: String? = null,
    var type: String? = null,
    var bio: String? = null,
    var bookmarked: Boolean
)