package com.example.focusstartsample.login.presentation

import android.util.Patterns
import com.example.focusstartsample.login.data.model.LoggedInUser
import com.example.focusstartsample.login.domain.LoginUseCase
import com.example.focusstartsample.login.domain.entity.Result

class LoginPresenterImpl(private val loginUseCase: LoginUseCase) : LoginPresenter {

    private var view: LoginView? = null

    override fun attachView(view: LoginView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun onLoginButtonClicked(username: String, password: String) {
        view?.showProgress()

        val result = loginUseCase(username, password)
        handleLoginResult(result)
    }

    private fun handleLoginResult(result: Result<LoggedInUser>) {
        view?.hideProgress()
        view?.toggleLoginButton(enable = true)

        if (result is Result.Success) {
            view?.updateUiWithUser(result.data.displayName)
        } else {
            view?.showLoginFailed()
        }
    }

    override fun onLoginDataUpdated(username: String, password: String) {
        handleLoginData(username, password)
    }

    private fun handleLoginData(username: String, password: String) {
        if (!isUserNameValid(username)) {
            view?.showUsernameError()
            view?.toggleLoginButton(enable = false)
        } else if (!isPasswordValid(password)) {
            view?.showPasswordError()
            view?.toggleLoginButton(enable = false)
        } else {
            view?.toggleLoginButton(enable = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.isNotBlank() && username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            false
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}