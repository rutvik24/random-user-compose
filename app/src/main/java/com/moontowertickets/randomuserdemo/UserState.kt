package com.moontowertickets.randomuserdemo

import com.moontowertickets.randomuserdemo.data.remote.UserDto

data class UserState (
    val users: List<UserDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)