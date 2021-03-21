package id.fadillah.fundamentalsubmission.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.fadillah.fundamentalsubmission.data.GithubUserRepository
import id.fadillah.fundamentalsubmission.data.model.UserEntity

class HomeViewModel(private val repository: GithubUserRepository): ViewModel() {
    fun getAllData(): LiveData<List<UserEntity>> = repository.loadAllUser()
}