package id.fadillah.fundamentalsubmission.ui.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.domain.usecase.GithubUserUseCase

class DetailViewModel(private val githubUserUseCase: GithubUserUseCase): ViewModel() {
    fun getDetailUser(username: String): LiveData<UserEntity> = githubUserUseCase.loadDetailUser(username)
}