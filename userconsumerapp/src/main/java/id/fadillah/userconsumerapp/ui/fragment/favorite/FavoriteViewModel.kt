package id.fadillah.userconsumerapp.ui.fragment.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.fadillah.userconsumerapp.data.model.UserEntity
import id.fadillah.userconsumerapp.domain.usecase.GithubUserUseCase

class FavoriteViewModel(private val githubUserUseCase: GithubUserUseCase) : ViewModel() {
    fun getListFavoriteUser(): LiveData<List<UserEntity>> = githubUserUseCase.loadFavoriteUser()
    fun getSearchListFavoriteUser(username: String): LiveData<List<UserEntity>> =
        githubUserUseCase.loadSearchFavoriteUser(username)
}