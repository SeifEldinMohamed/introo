package com.seif.introslider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class pageAdapter : PagerAdapter {
     var layouts: IntArray
     var inflater: LayoutInflater
     var con: Context
    constructor(layouts: IntArray, con: Context) : super() {
        this.layouts = layouts
        this.con = con
        inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
    override fun getCount(): Int {
        return layouts.size
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val veiw: View = inflater.inflate(layouts[position], container, false)
        container.addView(veiw)
        return veiw
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val veiw: View = `object` as View
        container.removeView(veiw)
    }
}