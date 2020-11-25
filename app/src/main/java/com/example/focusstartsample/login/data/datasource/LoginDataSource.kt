package com.example.focusstartsample.login.data.datasource

import com.example.focusstartsample.login.domain.entity.Result
import com.example.focusstartsample.login.data.model.LoggedInUser
import java.io.IOException
import java.util.UUID.randomUUID

interface LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser>
}

class LoginDataSourceImpl : LoginDataSource {

    override fun login(username: String, password: String): Result<LoggedInUser> =
        try {
            val fakeUser = LoggedInUser(randomUUID().toString(), "Jane Doe")
            Result.Success(fakeUser)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
}