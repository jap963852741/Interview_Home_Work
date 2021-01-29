package com.jap.interviewhomework.ui.main

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.jap.interviewhomework.databinding.ActivityMainBinding
import com.jap.interviewhomework.ui.home.HomeFragment
import com.jap.interviewhomework.util.FragmentSwitchUtil

class MainActivity : AppCompatActivity() {
    private lateinit var viewbinding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //Statusbar 轉為深色
        FragmentSwitchUtil(supportFragmentManager).getInstance().init(HomeFragment())
    }

}