package com.example.newsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapplication.databinding.ActivityMainBinding
import com.example.newsapplication.presentation.TopHeadlinesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var activityBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding?.root)

        if (savedInstanceState == null) {
            activityBinding?.fragmentContainer?.let {
                supportFragmentManager.beginTransaction()
                    .replace(it.id, TopHeadlinesFragment())
                    .commit()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityBinding = null
    }
}