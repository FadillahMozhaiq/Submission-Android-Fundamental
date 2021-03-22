package id.fadillah.fundamentalsubmission.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import id.fadillah.fundamentalsubmission.data.model.RepositoryEntity
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.data.source.local.LocalDataSource
import id.fadillah.fundamentalsubmission.data.source.network.RemoteDataSource
import id.fadillah.fundamentalsubmission.domain.repository.IGithubUserRepository
import id.fadillah.fundamentalsubmission.util.DataMapper

class GithubUserRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IGithubUserRepository {
    companion object {
        private var instance: GithubUserRepository? = null

        fun getInstance(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource) =
            instance ?: synchronized(this) {
                instance ?: GithubUserRepository(localDataSource, remoteDataSource)
            }
    }

    override fun loadAllUser(): LiveData<List<UserEntity>> =
        Transformations.map(remoteDataSource.getAllUser()) {
            DataMapper.listUserResponseToEntity(it)
        }

    override fun loadSearchUser(query: String): LiveData<List<UserEntity>> =
        Transformations.map(remoteDataSource.getSearchUser(query)) {
            DataMapper.listSearchUserResponseToEntity(it)
        }

    override fun loadDetailUser(username: String): LiveData<UserEntity> =
        Transformations.map(remoteDataSource.getDetailUser(username)) {
            DataMapper.detailUserResponseToEntity(it)
        }

    override fun loadListRepository(username: String): LiveData<List<RepositoryEntity>> =
        Transformations.map(remoteDataSource.getRepositoryUser(username)) {
            DataMapper.repositoryResponseToListEntity(it)
        }

    override fun loadListFollowers(username: String): LiveData<List<UserEntity>> =
        Transformations.map(remoteDataSource.getFollowersUser(username)) {
            DataMapper.listFollowersResponseToEntity(it)
        }

    override fun loadListFollowing(username: String): LiveData<List<UserEntity>> =
        Transformations.map(remoteDataSource.getFollowingUser(username)) {
            DataMapper.listFollowingResponseToEntity(it)
        }
}