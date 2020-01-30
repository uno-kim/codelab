package com.unokim.codelab.observable

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.unokim.codelab.observable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        // Display the navigation drawer
//        NavigationUI.setupWithNavController(binding.navView, navController)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.id.myNavHostFragment)
//        val ret = NavigationUI.navigateUp(navController, drawerLayout)
//        // drawerLayout.openDrawer(GravityCompat.START) is called in NavigationUI.navigateUp(),
//        // so we need to call closeDrawer(GravityCompat.START) below NavigationUI.navigateUp()
//        closeDrawer()
//        return ret
//    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        closeDrawer()
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (!closeDrawer()) {
            super.onBackPressed()
        }
    }

    private fun closeDrawer(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }
}
