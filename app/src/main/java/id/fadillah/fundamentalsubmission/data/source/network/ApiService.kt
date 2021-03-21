package id.fadillah.fundamentalsubmission.data.source.network

import id.fadillah.fundamentalsubmission.data.source.network.response.*
import id.fadillah.fundamentalsubmission.util.ConstantsHelper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    @Headers("Authorization: token ${ConstantsHelper.TOKEN}")
    fun getListUser(): Call<List<ItemsUserResponse>>

    @GET("search/users")
    @Headers("Authorization: token ${ConstantsHelper.TOKEN}")
    fun getSearchUser(@Query("q") q: String): Call<SearchUserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token ${ConstantsHelper.TOKEN}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("{username}/repos")
    @Headers("Authorization: token ${ConstantsHelper.TOKEN}")
    fun getRepositoryUser(
        @Path("username") username: String
    ): Call<List<ItemRepositoryResponse>>

    @GET("{username}/followers")
    @Headers("Authorization: token ${ConstantsHelper.TOKEN}")
    fun getFollowersUser(
        @Path("username") username: String
    ): Call<List<ItemFollowerResponse>>

    @GET("{username}/following")
    @Headers("Authorization: token ${ConstantsHelper.TOKEN}")
    fun getFollowingUser(
        @Path("username") username: String
    ): Call<List<ItemFollowingResponse>>
}