package com.lvqingyang.iwuster.Dean

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.SeekBar
import com.lvqingyang.frame.base.BaseActivity
import com.lvqingyang.frame.helper.str
import com.lvqingyang.iwuster.R
import com.lvqingyang.iwuster.drawable.CourseBgDrawable
import kotlinx.android.synthetic.main.activity_class_schedule_style.*

class ClassScheduleStyleActivity : BaseActivity() {
    private var mCorner=15f
    private var mAlpha=0.5f
    private var mInitCp=0
    private var mInitAp=0

    companion object {

        public fun newIntent(c: Context)=Intent(c, ClassScheduleStyleActivity::class.java)
    }

    override fun getLayoutResID()=R.layout.activity_class_schedule_style

    override fun initListener() {
        sb_alpha.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mAlpha=getAlphaByProgress(progress)
                changeClassSahpe()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        sb_corner.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mCorner=getCornerByProgress(progress)
                changeClassSahpe()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    override fun loadData() {
        mAlpha=myPreference.getFloat(str(R.string.sp_alpha), 0.5f)
        mCorner=myPreference.getFloat(str(R.string.sp_corner), 15f)
    }

    override fun showData() {
        initActionbar(R.string.theme, true)
        mInitAp=getProgressByAlpha(mAlpha)
        mInitCp=getProgressByCorner(mCorner)
        sb_alpha.progress=mInitAp
        sb_corner.progress=mInitCp
        changeClassSahpe()
    }

    private fun changeClassSahpe() {
        tv_test.background=CourseBgDrawable(0x168eea, mAlpha, mCorner)
    }

    override fun finish() {
        val ap=sb_alpha.progress
        val cp=sb_corner.progress
        if(ap!=mInitAp || cp!=mInitCp){
            myPreference.saveFloat(str(R.string.sp_alpha), mAlpha)
            myPreference.saveFloat(str(R.string.sp_corner), mCorner)
            setResult(Activity.RESULT_OK)
        }
        super.finish()
    }


    private fun getAlphaByProgress(p: Int)=p/100f*0.8f+0.2f
    private fun getCornerByProgress(p: Int)=p/100f*40
    private fun getProgressByAlpha(a: Float)=((a-0.2f)/0.8f*100).toInt()
    private fun getProgressByCorner(c: Float)=(c/40*100).toInt()

}
