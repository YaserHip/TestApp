package com.example.phunapp

import android.app.Application

class PhunApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: PhunApp
    }

    fun getStringFromRes(id: Int): String {
        return instance.resources.getString(id)
    }

}