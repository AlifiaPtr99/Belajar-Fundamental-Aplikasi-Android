package com.dicoding.bangkit.githubuser_submisi2.API

import com.dicoding.bangkit.githubuser_submisi2.dataClass.GithubUser
import com.dicoding.bangkit.githubuser_submisi2.dataClass.GithubUserResponse
import com.dicoding.bangkit.githubuser_submisi2.dataClass.DetailGithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ConfigApi {
        @GET("search/users")
        fun getCariUser(
            @Query("q") query : String
        ): Call<GithubUserResponse>

        @GET("users/{username}")
        fun getCariDetailUser(
            @Path("username") username : String
        ): Call<DetailGithubUser>

        @GET("users/{username}/followers")
        fun getCariDetailUserFollowers(
        @Path("username") username: String
        ): Call<ArrayList<GithubUser>>

        @GET("users/{username}/following")
        fun getCariDetailUserFollowing(
        @Path("username") username: String
        ): Call<ArrayList<GithubUser>>
}