package id.fadillah.fundamentalsubmission.data

import androidx.lifecycle.LiveData
import id.fadillah.fundamentalsubmission.data.model.RepositoryEntity
import id.fadillah.fundamentalsubmission.data.model.UserEntity

interface UserDataSource {
    fun loadAllUser(): LiveData<List<UserEntity>>
    fun loadSearchUser(): LiveData<List<UserEntity>>
    fun loadDetailUser(): LiveData<UserEntity>
    fun loadListRepository(username: String): LiveData<List<RepositoryEntity>>
    fun loadListFollowers(username: String): LiveData<List<UserEntity>>
    fun loadListFollowing(username: String): LiveData<List<UserEntity>>
}