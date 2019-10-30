package com.example.mydashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        /*DaggerApplicationGraph.builder()
            .applicationModule(AppModule(application))
            .roomModule(RoomModule())
            .viewModelFactoryModule(ViewModelFactoryModule())
            .build()
            .inject(this)*/
    }
}
