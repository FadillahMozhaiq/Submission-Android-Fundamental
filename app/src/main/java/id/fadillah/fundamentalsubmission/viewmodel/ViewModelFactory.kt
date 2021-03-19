package id.fadillah.fundamentalsubmission.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.fadillah.fundamentalsubmission.data.GithubUserRepository
import id.fadillah.fundamentalsubmission.ui.activity.detail.DetailViewModel
import id.fadillah.fundamentalsubmission.ui.activity.main.MainViewModel
import id.fadillah.fundamentalsubmission.util.Injection

class ViewModelFactory(private val repository: GithubUserRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        private var instance: ViewModelFactory? = null
        
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->
                DetailViewModel() as T
            else -> throw Throwable("Unknown ViewModel Class ${modelClass.name}")
        }
    }
}