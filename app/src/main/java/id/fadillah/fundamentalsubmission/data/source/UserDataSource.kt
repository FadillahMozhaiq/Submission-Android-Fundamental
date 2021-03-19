package id.fadillah.fundamentalsubmission.data.source

import androidx.lifecycle.LiveData
import id.fadillah.fundamentalsubmission.data.model.UserEntity

interface UserDataSource {
    fun loadAllUser(): LiveData<List<UserEntity>>
}