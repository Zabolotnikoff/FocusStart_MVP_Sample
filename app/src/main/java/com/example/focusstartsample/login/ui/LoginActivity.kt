package com.example.focusstartsample.login.ui

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.focusstartsample.R
import com.example.focusstartsample.login.di.LoginPresenterFactory
import com.example.focusstartsample.login.presentation.LoginPresenter
import com.example.focusstartsample.login.presentation.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initPresenter()
        initViews()
    }

    private fun initPresenter() {
        presenter = LoginPresenterFactory.create()
        presenter?.attachView(this)
    }

    private fun initViews() {
        username.afterTextChanged {
            presenter?.onLoginDataUpdated(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                presenter?.onLoginDataUpdated(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        presenter?.onLoginButtonClicked(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE

                presenter?.onLoginButtonClicked(
                    username.text.toString(),
                    password.text.toString()
                )
            }
        }
    }

    override fun updateUiWithUser(userName: String) {
        val welcome = getString(R.string.welcome)
        Toast.makeText(applicationContext, "$welcome $userName", Toast.LENGTH_LONG).show()

        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun showLoginFailed() {
        Toast.makeText(applicationContext, R.string.login_failed, Toast.LENGTH_SHORT).show()
    }

    override fun showUsernameError() {
        username.error = getString(R.string.invalid_username)
    }

    override fun showPasswordError() {
        password.error = getString(R.string.invalid_password)
    }

    override fun toggleLoginButton(enable: Boolean) {
        login.isEnabled = enable
    }

    override fun showProgress() {
        loading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loading.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter?.detachView()
        super.onDestroy()
    }
}

private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}