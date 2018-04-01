package com.lvqingyang.iwuster.Dean

import android.os.Bundle
import android.util.Log
import com.lvqingyang.frame.base.BaseActivity
import com.lvqingyang.frame.helper.jsonToList
import com.lvqingyang.frame.helper.str
import com.lvqingyang.iwuster.BuildConfig
import com.lvqingyang.iwuster.R
import com.lvqingyang.iwuster.bean.Score
import com.lvqingyang.iwuster.net.getXscjcx
import kotlinx.android.synthetic.main.score_activity.*
import org.jetbrains.anko.doAsync

class ScoreActivity : BaseActivity() {
    override fun getLayoutResID()=R.layout.score_activity

    override fun initListener() {
    }

    override fun loadData() {
        doAsync {
            val scores= getXscjcx(myPreference.getString(str(R.string.sp_stu_id)))
            val list=scores.jsonToList<Score>()
            val sb=StringBuilder()
            var term=""
            list.sortedBy { it.kkxq }
                    .reversed()
                    .forEach {
                        if(it.kkxq != term){
                            term=it.kkxq
                            sb.append("\n" +
                                    "------------------------------\n$term\n------------------------------\n")
                        }
                        sb.append("${it.kcmc}    ${it.xf}    ${it.zcj}    ${it.jd}\n\n")
                    }
            runOnUiThread { tv_socre.text=sb.toString() }
        }
    }

    override fun showData() {
        initActionbar(R.string.socre, true)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (BuildConfig.DEBUG) Log.d("ScoreActivity", "onCreate: ")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        if (BuildConfig.DEBUG) Log.d("ScoreActivity", "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        if (BuildConfig.DEBUG) Log.d("ScoreActivity", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        if (BuildConfig.DEBUG) Log.d("ScoreActivity", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        if (BuildConfig.DEBUG) Log.d("ScoreActivity", "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (BuildConfig.DEBUG) Log.d("ScoreActivity", "onDestroy: ")
    }
}
