package com.lvqingyang.iwuster

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.PopupWindow
import com.lvqingyang.frame.helper.PopupWindowBuilder
import com.lvqingyang.frame.helper.show
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    lateinit var mPopup: PopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mPopup=PopupWindowBuilder(this)
                .setContentView(R.layout.layout_popup_test, {})
                .setOutsideTouchable(true)
                .setFocusable(true)
                .create()

        btn_bottom.setOnClickListener { mPopup.show(it, PopupWindowBuilder.Direction.BOTTOM) }

        btn_top.setOnClickListener { mPopup.show(it) }

        btn_left.setOnClickListener { mPopup.show(it, PopupWindowBuilder.Direction.LEFT) }

        btn_right.setOnClickListener { mPopup.show(it, PopupWindowBuilder.Direction.RIGHT) }

    }
}
