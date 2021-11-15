package com.example.mvvmsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.databinding.ActivityLoginBinding
import com.example.mvvmsampleapp.util.hide
import com.example.mvvmsampleapp.util.show
import com.example.mvvmsampleapp.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login) //connect the layout which is connected to viewModel
        val viewModel = ViewModelProvider(this).get(AuthViewModel::class.java) //get the ViewModel instance
        binding.viewmodel = viewModel //get the viewModel which is connected to the layout
        viewModel.authListener = this //get the listener which is connected to the viewModel (current class contains AuthListener->this)
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, Observer {
            toast(it) //display the message(it) with toast
            progress_bar.hide()
        })
    }

    override fun onFailure(message: String) {
        toast(message)
        progress_bar.hide()
    }
}