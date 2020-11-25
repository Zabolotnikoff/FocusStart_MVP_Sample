package com.example.focusstartsample.login.presentation

interface LoginView {

    fun updateUiWithUser(userName: String)

    fun showLoginFailed()

    fun showUsernameError()

    fun showPasswordError()

    fun toggleLoginButton(enable: Boolean)

    fun showProgress()

    fun hideProgress()
}