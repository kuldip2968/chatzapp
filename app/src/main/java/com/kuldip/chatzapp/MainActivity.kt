package com.kuldip.chatzapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.kuldip.chatzapp.activity.NumberActivity
import com.kuldip.chatzapp.adapter.ViewPagerAdapter
import com.kuldip.chatzapp.databinding.ActivityMainBinding
import com.kuldip.chatzapp.ui.CallFragment
import com.kuldip.chatzapp.ui.ChatFragment
import com.kuldip.chatzapp.ui.StatusFragment

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val fragmentArrayList = ArrayList<Fragment>()

        fragmentArrayList.add((ChatFragment()))
        fragmentArrayList.add((StatusFragment()))
        fragmentArrayList.add((CallFragment()))

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null){
            startActivity(Intent(this, NumberActivity::class.java))
            finish()
        }

        val adapter = ViewPagerAdapter(this,supportFragmentManager,fragmentArrayList)

        binding!!.viewPager.adapter = adapter

        binding!!.tabs.setupWithViewPager(binding!!.viewPager)

    }
}