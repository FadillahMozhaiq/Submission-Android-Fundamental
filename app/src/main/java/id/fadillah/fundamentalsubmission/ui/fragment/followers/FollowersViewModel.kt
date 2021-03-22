package id.fadillah.fundamentalsubmission.ui.fragment.followers

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.domain.usecase.GithubUserUseCase

class FollowersViewModel(private val githubUserUseCase: GithubUserUseCase): ViewModel() {
    fun getListFollowers(username: String): LiveData<List<UserEntity>> = githubUserUseCase.loadListFollowers(username)
    fun getSearchListFollowers(username: String, name: String) = Transformations.map(githubUserUseCase.loadListFollowers(username)) {
        it.filter { item ->
            item.username?.contains(name, ignoreCase = true) ?: false
        }
    }
}