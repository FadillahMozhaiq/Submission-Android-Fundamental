package id.fadillah.fundamentalsubmission.ui.fragment.followers

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.domain.usecase.GithubUserUseCase

class FollowersViewModel(private val githubUserUseCase: GithubUserUseCase): ViewModel() {
    fun getListFollowers(username: String): LiveData<List<UserEntity>> = githubUserUseCase.loadListFollowers(username)
}