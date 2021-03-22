package id.fadillah.fundamentalsubmission.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.fadillah.fundamentalsubmission.domain.usecase.GithubUserUseCase
import id.fadillah.fundamentalsubmission.ui.activity.detail.DetailViewModel
import id.fadillah.fundamentalsubmission.ui.fragment.favorite.FavoriteViewModel
import id.fadillah.fundamentalsubmission.ui.fragment.followers.FollowersViewModel
import id.fadillah.fundamentalsubmission.ui.fragment.following.FollowingViewModel
import id.fadillah.fundamentalsubmission.ui.fragment.home.HomeViewModel
import id.fadillah.fundamentalsubmission.ui.fragment.repository.RepositoryViewModel
import id.fadillah.fundamentalsubmission.util.Injection

class ViewModelFactory(private val githubUserUseCase: GithubUserUseCase): ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var instance: ViewModelFactory? = null
        
        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideGithubUseCase())
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(githubUserUseCase) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->
                DetailViewModel(githubUserUseCase) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) ->
                FavoriteViewModel(githubUserUseCase) as T
            modelClass.isAssignableFrom(FollowersViewModel::class.java) ->
                FollowersViewModel(githubUserUseCase) as T
            modelClass.isAssignableFrom(FollowingViewModel::class.java) ->
                FollowingViewModel(githubUserUseCase) as T
            modelClass.isAssignableFrom(RepositoryViewModel::class.java) ->
                RepositoryViewModel(githubUserUseCase) as T
            else -> throw Throwable("Unknown ViewModel Class ${modelClass.name}")
        }
    }
}