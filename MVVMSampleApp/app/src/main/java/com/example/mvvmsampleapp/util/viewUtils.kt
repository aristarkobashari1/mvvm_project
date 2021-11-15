package com.example.mvvmsampleapp.util

import android.content.Context
import android.media.tv.TvContract
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT);
}

fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    visibility = View.GONE
}