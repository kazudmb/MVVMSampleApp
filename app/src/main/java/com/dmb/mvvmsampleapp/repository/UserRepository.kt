package com.dmb.mvvmsampleapp.repository

import com.dmb.mvvmsampleapp.network.MyApi
import com.dmb.mvvmsampleapp.network.responses.AuthResponse
import com.dmb.mvvmsampleapp.network.responses.SafeApiRequest

class UserRepository : SafeApiRequest(){

    suspend fun userLogin(email: String, password: String) : AuthResponse {

        return apiRequest { MyApi().userLogin(email, password) }
    }

}