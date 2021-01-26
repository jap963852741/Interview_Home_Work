package com.jap.interviewhomework.ui.main


import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.jap.interviewhomework.R
import com.jap.interviewhomework.databinding.ActivityMainBinding
import com.jap.interviewhomework.ui.home.HomeFragment



class MainActivity : AppCompatActivity() {
    private lateinit var viewbinding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR //Statusbar 轉為深色
//        navigation = viewbinding.navView
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.nav_host_fragment, HomeFragment())
        fragmentTransaction.commit()
    }

}