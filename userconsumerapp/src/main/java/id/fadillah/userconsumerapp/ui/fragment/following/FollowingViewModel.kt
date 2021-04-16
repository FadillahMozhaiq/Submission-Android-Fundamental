package id.fadillah.userconsumerapp.ui.fragment.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.fadillah.userconsumerapp.data.model.UserEntity
import id.fadillah.userconsumerapp.domain.usecase.GithubUserUseCase

class FollowingViewModel(private val githubUserUseCase: GithubUserUseCase) : ViewModel() {
    private var userEntity: UserEntity? = null

    fun getUser(): UserEntity? = userEntity

    fun setUser(user: UserEntity) {
        userEntity = user
    }

    fun getListFollowing(username: String): LiveData<List<UserEntity>> =
        githubUserUseCase.loadListFollowing(username)

    fun getSearchListFollowing(username: String, query: String): LiveData<List<UserEntity>> =
        Transformations.map(githubUserUseCase.loadListFollowing(username)) { list ->
            list.filter {
                it.username?.contains(query, ignoreCase = true) ?: false
            }
        }
}