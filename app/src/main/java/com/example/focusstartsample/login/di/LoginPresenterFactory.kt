package com.example.focusstartsample.login.di

import com.example.focusstartsample.login.data.datasource.LoginDataSourceImpl
import com.example.focusstartsample.login.data.repository.LoginRepositoryImpl
import com.example.focusstartsample.login.domain.LoginUseCase
import com.example.focusstartsample.login.presentation.LoginPresenter
import com.example.focusstartsample.login.presentation.LoginPresenterImpl

object LoginPresenterFactory {

    fun create(): LoginPresenter {
        val loginDataSource = LoginDataSourceImpl()
        val loginRepository = LoginRepositoryImpl(loginDataSource)
        val loginUseCase = LoginUseCase(loginRepository)

        return LoginPresenterImpl(loginUseCase)
    }
}