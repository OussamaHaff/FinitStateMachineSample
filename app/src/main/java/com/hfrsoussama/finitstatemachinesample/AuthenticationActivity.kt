package com.hfrsoussama.finitstatemachinesample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class AuthenticationActivity : AppCompatActivity(), View.OnClickListener{

    private val stateObserver by lazy {
        Observer<State> { renderViewState(it) }
    }

    private fun renderViewState(state: State) {
        txtActualState?.text = state.toString()
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(AuthenticationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.currentState.observe(this, stateObserver)

        btnLogin.setOnClickListener(this)
        btnRefresh.setOnClickListener(this)
        btnLogout.setOnClickListener(this)
        btnReset.setOnClickListener(this)

        txtActualState?.text = viewModel.currentState.value.toString()
    }

    override fun onClick(v: View?) {
        when (v) {
            btnLogin -> viewModel.updateState(Action.Login())
            btnRefresh -> viewModel.updateState(Action.Refresh())
            btnLogout -> viewModel.updateState(Action.Logout())
            btnReset -> viewModel.resetState()
        }
    }
}
