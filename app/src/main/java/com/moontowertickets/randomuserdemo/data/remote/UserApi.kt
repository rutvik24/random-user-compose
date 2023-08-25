package com.moontowertickets.randomuserdemo.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/")
    suspend fun getUsers(
        @Query("page") page: Int? = 1,
        @Query("results") results: Int? = 20,
    ): UsersResponse
}