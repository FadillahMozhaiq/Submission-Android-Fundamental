package id.fadillah.userconsumerapp.util.injection

import android.content.Context
import id.fadillah.userconsumerapp.data.GithubUserRepository
import id.fadillah.userconsumerapp.data.source.local.LocalDataSource
import id.fadillah.userconsumerapp.data.source.network.RemoteDataSource
import id.fadillah.userconsumerapp.domain.usecase.GithubUserInteractor
import id.fadillah.userconsumerapp.domain.usecase.GithubUserUseCase

object Injection {
    private fun provideRepository(context: Context): GithubUserRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return GithubUserRepository.getInstance(provideLocalDataSource(context), remoteDataSource)
    }

    private fun provideLocalDataSource(context: Context): LocalDataSource =
        LocalDataSource(context)

    fun provideGithubUseCase(context: Context): GithubUserUseCase {
        val repository = provideRepository(context)
        return GithubUserInteractor(repository)
    }
}