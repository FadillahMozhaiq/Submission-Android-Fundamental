package id.fadillah.userconsumerapp.ui.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.fadillah.userconsumerapp.data.model.UserEntity
import id.fadillah.userconsumerapp.domain.usecase.GithubUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val githubUserUseCase: GithubUserUseCase): ViewModel() {
    fun getDetailUser(username: String): LiveData<UserEntity> = githubUserUseCase.loadDetailUser(username)
    fun isFavoriteUser(username: String): LiveData<Boolean> = githubUserUseCase.loadIsFavoriteUser(username)
    fun setFavoriteUser(userEntity: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            githubUserUseCase.setFavoriteUser(userEntity)
        }
    }
}