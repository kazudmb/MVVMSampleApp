package com.dmb.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import com.dmb.mvvmsampleapp.repository.UserRepository

class ProfileViewModel(
    private val repository: UserRepository
) : ViewModel() {
    val user = repository.getUser()


}