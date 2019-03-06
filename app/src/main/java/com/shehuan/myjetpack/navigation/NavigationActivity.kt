package com.shehuan.myjetpack.navigation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.shehuan.myjetpack.R
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {
    companion object {
        fun start(context: Activity) {
            val intent = Intent(context, NavigationActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        useToolbar()

//        useBottomNavigationView()
    }

    private fun useBottomNavigationView() {
//        val navController = findNavController(this, R.id.bottom_nav_host_fragment)
//        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    private fun useToolbar() {
        toolbar.run {
            title = ""
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val navController = findNavController(this, R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
    }
}
