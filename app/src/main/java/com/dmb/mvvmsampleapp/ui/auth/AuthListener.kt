package com.dmb.mvvmsampleapp.ui.auth

import com.dmb.mvvmsampleapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}