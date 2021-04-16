package id.fadillah.userconsumerapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.fadillah.userconsumerapp.domain.usecase.GithubUserUseCase
import id.fadillah.userconsumerapp.ui.activity.detail.DetailViewModel
import id.fadillah.userconsumerapp.ui.fragment.favorite.FavoriteViewModel
import id.fadillah.userconsumerapp.ui.fragment.followers.FollowersViewModel
import id.fadillah.userconsumerapp.ui.fragment.following.FollowingViewModel
import id.fadillah.userconsumerapp.ui.fragment.home.HomeViewModel
import id.fadillah.userconsumerapp.ui.fragment.repository.RepositoryViewModel
import id.fadillah.userconsumerapp.util.injection.Injection

class ViewModelFactory(private val githubUserUseCase: GithubUserUseCase): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideGithubUseCase(context))
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