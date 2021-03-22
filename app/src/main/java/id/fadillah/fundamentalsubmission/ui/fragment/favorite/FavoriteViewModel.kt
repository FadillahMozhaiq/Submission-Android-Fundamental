package id.fadillah.fundamentalsubmission.ui.fragment.favorite

import androidx.lifecycle.ViewModel
import id.fadillah.fundamentalsubmission.domain.usecase.GithubUserUseCase

class FavoriteViewModel(private val githubUserUseCase: GithubUserUseCase): ViewModel() {
    fun getListFavoriteUser() = Unit
}