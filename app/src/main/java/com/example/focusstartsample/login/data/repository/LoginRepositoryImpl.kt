package com.example.focusstartsample.login.data.repository

import com.example.focusstartsample.login.data.datasource.LoginDataSource
import com.example.focusstartsample.login.data.model.LoggedInUser
import com.example.focusstartsample.login.domain.entity.Result
import com.example.focusstartsample.login.domain.repository.LoginRepository

class LoginRepositoryImpl(private val dataSource: LoginDataSource) : LoginRepository {

    override fun login(username: String, password: String): Result<LoggedInUser> =
        dataSource.login(username, password)
}