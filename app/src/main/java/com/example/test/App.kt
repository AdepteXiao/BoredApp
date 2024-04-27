package com.example.test

import android.app.Application
import com.example.test.data.Db

class App : Application() {
    val database by lazy { Db.createDb(this)}
}