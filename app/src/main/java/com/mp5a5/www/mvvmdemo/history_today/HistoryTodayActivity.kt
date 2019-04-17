package com.mp5a5.www.mvvmdemo.history_today

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.mp5a5.www.mvvmdemo.R
import com.mp5a5.www.mvvmdemo.mvvm.BaseMVVMActivity
import com.mp5a5.www.mvvmdemo.mvvm.LiveDataBus
import com.mp5a5.www.mvvmdemo.mvvm.LiveDataEntity
import kotlinx.android.synthetic.main.activity_history_today.*
import org.jetbrains.anko.toast

/**
 * @describe
 * @author ：mp5a5 on 2019/4/17 13：21
 * @email：wwb199055@126.com
 */
class HistoryTodayActivity : BaseMVVMActivity<HistoryTodayViewModel>() {

    private lateinit var mAdapter: HistoryTodayAdapter
    private val mList = mutableListOf<HistoryTodayEntity.ResultEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_today)
        initAdapter()
        initData()
    }

    private fun initData() {
        mViewModel.getTodayResult("cd80790e2a20c97e793c7ed8acecfac6", "1.0", 4, 1, this)
    }

    private fun initAdapter() {
        mAdapter = HistoryTodayAdapter(mList)
        lvList.adapter = mAdapter
    }

    override fun dataObserver() {

        LiveDataBus.getDefault().subscribe<LiveDataEntity>("HistoryTodayViewModel").observe(this, Observer { msg->
            msg?.let {
                toast(it.msg)
            }

        })
        LiveDataBus.getDefault().subscribe<HistoryTodayEntity>("HistoryTodayViewModel", "s")
            .observe(this, Observer { data ->
                data?.result?.let {
                    mList.addAll(it)
                    mAdapter.notifyDataSetChanged()
                }
            })

    }

}