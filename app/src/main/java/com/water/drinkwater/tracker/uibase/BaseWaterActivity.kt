package com.water.drinkwater.tracker.uibase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Date：2023/11/13
 * Describe:
 */
abstract class BaseWaterActivity : AppCompatActivity() {
    protected var isResume = false
    protected var isOnStop = false
    abstract val viewBinding: ViewBinding
    abstract fun initView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        initView()
    }

    override fun onStart() {
        super.onStart()
        isOnStop = false
    }

    override fun onResume() {
        super.onResume()
        isResume = true
    }

    override fun onPause() {
        super.onPause()
        isResume = false
    }

    override fun onStop() {
        super.onStop()
        isOnStop = true
    }
}