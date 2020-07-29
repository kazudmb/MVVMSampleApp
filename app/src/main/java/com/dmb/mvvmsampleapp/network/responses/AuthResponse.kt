package com.dmb.mvvmsampleapp.network.responses

import com.dmb.mvvmsampleapp.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)