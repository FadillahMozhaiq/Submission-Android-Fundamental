package id.fadillah.fundamentalsubmission.util.injection

import android.content.Context
import id.fadillah.fundamentalsubmission.data.GithubUserRepository
import id.fadillah.fundamentalsubmission.data.source.local.LocalDataSource
import id.fadillah.fundamentalsubmission.data.source.local.room.UserDatabase
import id.fadillah.fundamentalsubmission.data.source.network.RemoteDataSource
import id.fadillah.fundamentalsubmission.domain.usecase.GithubUserInteractor
import id.fadillah.fundamentalsubmission.domain.usecase.GithubUserUseCase

object Injection {
    private fun provideRepository(context: Context): GithubUserRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return GithubUserRepository.getInstance(provideLocalDataSource(context), remoteDataSource)
    }

    fun provideLocalDataSource(context: Context): LocalDataSource {
        val database = UserDatabase.getInstance(context)
        return LocalDataSource(database.userDao())
    }

    fun provideGithubUseCase(context: Context): GithubUserUseCase {
        val repository = provideRepository(context)
        return GithubUserInteractor(repository)
    }
}