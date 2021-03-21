package id.fadillah.fundamentalsubmission.ui.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.domain.usecase.GithubUserUseCase

class DetailViewModel(private val githubUserUseCase: GithubUserUseCase): ViewModel() {
    private var dataDetail = MutableLiveData<UserEntity?>()

    fun setData(data: UserEntity?) {
        dataDetail.value = data
    }

    fun loadData(): LiveData<UserEntity?> = dataDetail
}