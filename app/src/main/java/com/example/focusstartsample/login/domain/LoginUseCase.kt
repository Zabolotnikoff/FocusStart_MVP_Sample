package com.example.focusstartsample.login.domain

import com.example.focusstartsample.login.data.model.LoggedInUser
import com.example.focusstartsample.login.domain.entity.Result
import com.example.focusstartsample.login.domain.repository.LoginRepository

class LoginUseCase(private val loginRepository: LoginRepository) {

    operator fun invoke(username: String, password: String): Result<LoggedInUser> =
        loginRepository.login(username, password)
}