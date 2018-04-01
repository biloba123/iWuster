package com.lvqingyang.iwuster

import android.animation.Animator
import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.lvqingyang.frame.helper.ColorHelper
import kotlinx.android.synthetic.main.welcome_activity.*
import org.jetbrains.anko.startActivity

class WelcomeActivity : Activity() {
    private val SPLASH_TIME=1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //全屏
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        setContentView(R.layout.welcome_activity)

        val startColor=Color.parseColor("#009083")
        val endColor=Color.parseColor("#6dcec4")
        val animator=ValueAnimator.ofFloat(0f, 1f)
                .setDuration(SPLASH_TIME)
         animator.addUpdateListener {
                    ll.setBackgroundColor(ColorHelper.computeColor(startColor, endColor, it.animatedValue as Float))
                }
        animator.addListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                startActivity<MainActivity>()
                finish()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        animator.start()

    }
}
