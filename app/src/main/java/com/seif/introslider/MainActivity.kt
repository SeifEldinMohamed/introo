package com.seif.introslider

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.third_slider.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mpager: ViewPager
    var layouts: IntArray =
        intArrayOf(R.layout.first_slider, R.layout.second_slider, R.layout.third_slider)
    lateinit var dotslayout: LinearLayout
    lateinit var dots: Array<ImageView>
    lateinit var madapter: pageAdapter
    lateinit var btnNext: Button
    lateinit var btnstart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (prefManager(this).checkpref()) {
            startActivity(Intent(this, fragment_main::class.java))
            finish()
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        mpager = findViewById(R.id.pager) as ViewPager
        madapter = pageAdapter(layouts, this)
        mpager.adapter = madapter
        dotslayout = findViewById(R.id.dots) as LinearLayout
        btnNext = findViewById(R.id.btn_next)
        btnNext.setOnClickListener(this)
        createdots(0)

        mpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                createdots(position)
                if (position == layouts.size - 1) {
                    btnNext.setText("Start")
                } else {
                    btnNext.setText("Next")
                }
            }
        })
/*
        btn_next.setOnClickListener {
            var nextslide:Int=mpager.currentItem+1
            if (nextslide<layouts.size){
                mpager.setCurrentItem(nextslide)
            }else{

            }
        }
        btn_start.setOnClickListener {
            startActivity(Intent(this,fragment_main::class.java))
            finish()
            prefManager(this).writesp()
        }

 */
    }
    fun createdots(position: Int) {
        if (dotslayout != null) {
            dotslayout.removeAllViews()
        }
        dots = Array(layouts.size, { i -> ImageView(this) })
        for (i in 0..layouts.size - 1) {
            dots[i] = ImageView(this)
            if (i == position) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dots))

            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dots))

            }
            var params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(4, 0, 4, 0)
            dotslayout.addView(dots[i], params)

        }
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {

            R.id.btn_next -> {
                var nextslide: Int = mpager.currentItem + 1
                if (nextslide < layouts.size) {
                    mpager.setCurrentItem(nextslide)
                } else {
                    startActivity(Intent(this, fragment_main::class.java))
                    finish()
                    prefManager(this).writesp()
                }
            }
        }

    }

}