package id.fadillah.fundamentalsubmission.domain.usecase

import androidx.lifecycle.LiveData
import id.fadillah.fundamentalsubmission.data.model.RepositoryEntity
import id.fadillah.fundamentalsubmission.data.model.UserEntity
import id.fadillah.fundamentalsubmission.domain.repository.IGithubUserRepository

class GithubUserInteractor(private val githubUserRepository: IGithubUserRepository) :
    GithubUserUseCase {
    override fun loadAllUser(): LiveData<List<UserEntity>> = githubUserRepository.loadAllUser()

    override fun loadSearchUser(query: String): LiveData<List<UserEntity>> =
        githubUserRepository.loadSearchUser(query)

    override fun loadDetailUser(username: String): LiveData<UserEntity> =
        githubUserRepository.loadDetailUser(username)

    override fun loadListRepository(username: String): LiveData<List<RepositoryEntity>> =
        githubUserRepository.loadListRepository(username)

    override fun loadListFollowers(username: String): LiveData<List<UserEntity>> =
        githubUserRepository.loadListFollowers(username)

    override fun loadListFollowing(username: String): LiveData<List<UserEntity>> =
        githubUserRepository.loadListFollowing(username)

    override fun loadFavoriteUser(): LiveData<List<UserEntity>> =
        githubUserRepository.loadFavoriteUser()

    override fun loadSearchFavoriteUser(query: String): LiveData<List<UserEntity>> =
        githubUserRepository.loadSearchFavoriteUser(query)

    override fun loadIsFavoriteUser(username: String): LiveData<Boolean> =
        githubUserRepository.loadIsFavoriteUser(username)

    override suspend fun setFavoriteUser(userEntity: UserEntity) =
        githubUserRepository.setFavoriteUser(userEntity)
}