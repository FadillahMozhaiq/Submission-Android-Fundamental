package id.fadillah.fundamentalsubmission.ui.fragment.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.domain.usecase.GithubUserUseCase

class FollowingViewModel(private val githubUserUseCase: GithubUserUseCase): ViewModel()  {
    fun getListFollowing(username: String): LiveData<List<UserEntity>> = githubUserUseCase.loadListFollowing(username)
}