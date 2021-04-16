package id.fadillah.userconsumerapp.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.fadillah.userconsumerapp.data.model.UserEntity
import id.fadillah.userconsumerapp.domain.usecase.GithubUserUseCase

class HomeViewModel(private val githubUserUseCase: GithubUserUseCase): ViewModel() {
    fun getAllData(): LiveData<List<UserEntity>> = githubUserUseCase.loadAllUser()
    fun getSearchUsers(query: String): LiveData<List<UserEntity>> = githubUserUseCase.loadSearchUser(query)
}