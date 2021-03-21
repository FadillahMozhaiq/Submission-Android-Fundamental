package id.fadillah.fundamentalsubmission.data.source.network

import id.fadillah.fundamentalsubmission.data.source.network.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getListUser(): Call<List<ItemsUserResponse>>

    @GET("search/users")
    fun getSearchUser(@Query("q") q: String): Call<SearchUserResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("{username}/repos")
    fun getRepositoryUser(
        @Path("username") username: String
    ): Call<List<ItemRepositoryResponse>>

    @GET("{username}/followers")
    fun getFollowersUser(
        @Path("username") username: String
    ): Call<List<ItemFollowerResponse>>

    @GET("{username}/following")
    fun getFollowingUser(
        @Path("username") username: String
    ): Call<List<ItemFollowingResponse>>
}