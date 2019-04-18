package com.mp5a5.www.mvvmdemo.nba

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.mp5a5.www.mvvmdemo.R
import com.mp5a5.www.mvvmdemo.history_today.HistoryTodayActivity
import com.mp5a5.www.mvvmdemo.login.LoginActivity
import com.mp5a5.www.mvvmdemo.mvvm.BaseMVVMActivity
import com.mp5a5.www.mvvmdemo.mvvm.LiveDataBus
import kotlinx.android.synthetic.main.activity_main.*

class NBAActivity : BaseMVVMActivity<NBAViewModel>() {


    override fun dataObserver() {
        LiveDataBus.getDefault().subscribe<NBAEntity>("Article", NBAEntity::class.java)
            .observe(this, Observer<NBAEntity> { data ->
                data?.let {
                    tvText.text = it.msg
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getTabData()


        btnToday.setOnClickListener {
            startActivity(Intent(this, HistoryTodayActivity::class.java))
        }
        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun getTabData() {
        mViewModel.getNBAData("6949e822e6844ae6453fca0cf83379d3", this)
    }

}
