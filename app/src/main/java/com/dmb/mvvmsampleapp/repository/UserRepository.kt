package com.dmb.mvvmsampleapp.repository

import com.dmb.mvvmsampleapp.network.MyApi
import com.dmb.mvvmsampleapp.network.responses.AuthResponse
import retrofit2.Response

class UserRepository {

    suspend fun userLogin(email: String, password: String) : Response<AuthResponse> {
        return MyApi().userLogin(email, password)
    }

}