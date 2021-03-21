package id.fadillah.fundamentalsubmission.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    val username: String? = null,
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
    val createAt: String? = null
): Parcelable