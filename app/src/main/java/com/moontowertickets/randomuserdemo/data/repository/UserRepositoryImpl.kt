package com.moontowertickets.randomuserdemo.data.repository

import com.moontowertickets.randomuserdemo.data.remote.UserApi
import com.moontowertickets.randomuserdemo.data.remote.UsersResponse
import com.moontowertickets.randomuserdemo.domain.repository.UserRepository
import com.moontowertickets.randomuserdemo.domain.util.Resource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
): UserRepository {

    override suspend fun getUsers(page: Int?, results: Int?): Resource<UsersResponse> {
        return try {
            Resource.Success(
                data = api.getUsers(
                    page = page,
                    results = results
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("An unknown error occurred")
        }
    }
}