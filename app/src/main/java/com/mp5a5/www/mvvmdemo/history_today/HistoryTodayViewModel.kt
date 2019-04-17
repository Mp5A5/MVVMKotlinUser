package com.mp5a5.www.mvvmdemo.history_today

import android.app.Application
import com.mp5a5.www.mvvmdemo.mvvm.BaseViewModel
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * @describe
 * @author ：mp5a5 on 2019/4/15 13：34
 * @email：wwb199055@126.com
 */
class HistoryTodayViewModel(application: Application) : BaseViewModel<HistoryTodayRepository>(application) {

    fun getTodayResult(key: String, v: String, month: Int, day: Int, activity: RxAppCompatActivity) {
        mRepository.getTodayResult(key, v, month, day, activity)
    }
}