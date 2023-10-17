package com.example.bottomnavanddrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.bottomnavanddrawer.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var fragmnetManager: FragmentManager
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarID.inflateMenu(R.menu.nav_drawer_menu)
//        setSupportActionBar(binding.toolbarID)
        var toggle = ActionBarDrawerToggle(
            this,
            binding.DrawerLayoutId,
            R.string.nav_drawer_open,
            R.string.nav_drawer_close
        )

        binding.DrawerLayoutId.addDrawerListener(toggle)
        toggle.syncState()
        binding.navDrawer.setNavigationItemSelectedListener(this)
        binding.bottomNavBar.background = null
        binding.bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeID -> openFragmnet(Home())
                R.id.videoID -> openFragmnet(NewsFragment())
            }
            true
        }
        fragmnetManager = supportFragmentManager
        openFragmnet(Home())
        binding.toolbarID.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.trendingId ->
                    openFragmnet(Trending())

            }
            true
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.trendingId) {
            openFragmnet(Trending())
        }
//        return super.onOptionsItemSelected(item)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.trendingId -> openFragmnet(Trending())
            R.id.hotTopicsID -> openFragmnet(Home())
            R.id.sportsID -> openFragmnet(SportsFragment())
        }
        binding.DrawerLayoutId.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.DrawerLayoutId.isDrawerOpen(GravityCompat.START)) {
            binding.DrawerLayoutId.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun openFragmnet(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmnetManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentContainerID.id, fragment)
        fragmentTransaction.commit()
    }
}