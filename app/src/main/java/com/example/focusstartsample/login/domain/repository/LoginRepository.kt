package com.example.focusstartsample.login.domain.repository

import com.example.focusstartsample.login.domain.entity.Result
import com.example.focusstartsample.login.data.model.LoggedInUser

interface LoginRepository {

    fun login(username: String, password: String): Result<LoggedInUser>
}