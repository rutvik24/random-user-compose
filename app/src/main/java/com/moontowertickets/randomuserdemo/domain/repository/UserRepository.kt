package com.moontowertickets.randomuserdemo.domain.repository

import com.moontowertickets.randomuserdemo.data.remote.UsersResponse
import com.moontowertickets.randomuserdemo.domain.util.Resource

interface UserRepository {
    suspend fun getUsers(page: Int? = 1, results: Int? = 20): Resource<UsersResponse>
}