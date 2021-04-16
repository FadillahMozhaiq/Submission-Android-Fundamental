package id.fadillah.userconsumerapp.data.source.local.model

data class UserDatabaseEntity(
    var id: Long = 0,
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