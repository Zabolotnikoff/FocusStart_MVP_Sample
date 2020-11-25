package com.example.focusstartsample.login.presentation

interface LoginPresenter {

    fun attachView(view: LoginView)

    fun detachView()

    fun onLoginButtonClicked(username: String, password: String)

    fun onLoginDataUpdated(username: String, password: String)
}