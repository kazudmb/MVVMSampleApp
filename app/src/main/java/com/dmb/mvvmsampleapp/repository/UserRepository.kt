package com.dmb.mvvmsampleapp.repository

import com.dmb.mvvmsampleapp.data.db.AppDatabase
import com.dmb.mvvmsampleapp.data.db.entities.User
import com.dmb.mvvmsampleapp.network.MyApi
import com.dmb.mvvmsampleapp.network.responses.AuthResponse
import com.dmb.mvvmsampleapp.network.responses.SafeApiRequest

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest(){

    suspend fun userLogin(email: String, password: String) : AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()

}