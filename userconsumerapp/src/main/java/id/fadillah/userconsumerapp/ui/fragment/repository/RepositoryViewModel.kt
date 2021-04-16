package id.fadillah.userconsumerapp.ui.fragment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.fadillah.userconsumerapp.data.model.RepositoryEntity
import id.fadillah.userconsumerapp.data.model.UserEntity
import id.fadillah.userconsumerapp.domain.usecase.GithubUserUseCase

class RepositoryViewModel(private val githubUserUseCase: GithubUserUseCase): ViewModel()  {
    private var userEntity: UserEntity? = null

    fun getUser(): UserEntity? = userEntity

    fun setUser(user: UserEntity) {
        userEntity = user
    }

    fun getListRepository(username: String): LiveData<List<RepositoryEntity>> = githubUserUseCase.loadListRepository(username)
}