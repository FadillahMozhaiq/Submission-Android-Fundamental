package id.fadillah.fundamentalsubmission.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.fadillah.fundamentalsubmission.data.GithubUserRepository
import id.fadillah.fundamentalsubmission.data.model.UserEntity

class MainViewModel(private val repository: GithubUserRepository): ViewModel() {
    fun getAllData(): LiveData<List<UserEntity>> = repository.loadAllUser()
}