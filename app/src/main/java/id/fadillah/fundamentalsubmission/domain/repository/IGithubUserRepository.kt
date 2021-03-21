package id.fadillah.fundamentalsubmission.domain.repository

import androidx.lifecycle.LiveData
import id.fadillah.fundamentalsubmission.data.model.RepositoryEntity
import id.fadillah.fundamentalsubmission.data.model.UserEntity

interface IGithubUserRepository {
    fun loadAllUser(): LiveData<List<UserEntity>>
    fun loadSearchUser(query: String): LiveData<List<UserEntity>>
    fun loadDetailUser(username: String): LiveData<UserEntity>
    fun loadListRepository(username: String): LiveData<List<RepositoryEntity>>
    fun loadListFollowers(username: String): LiveData<List<UserEntity>>
    fun loadListFollowing(username: String): LiveData<List<UserEntity>>
}