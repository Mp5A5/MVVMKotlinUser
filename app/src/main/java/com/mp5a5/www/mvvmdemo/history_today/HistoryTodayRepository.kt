package com.mp5a5.www.mvvmdemo.history_today

import com.mp5a5.www.library.use.BaseObserver
import com.mp5a5.www.mvvmdemo.mvvm.BaseRepository
import com.mp5a5.www.mvvmdemo.mvvm.LiveDataEntity
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @describe
 * @author ：mp5a5 on 2019/4/15 13：35
 * @email：wwb199055@126.com
 */
class HistoryTodayRepository : BaseRepository() {

    fun getTodayResult(key: String, v: String, month: Int, day: Int, activity: RxAppCompatActivity) {
        HistoryTodayService
            .getTodayEvents(key, v, month, day)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(activity.bindToLifecycle())
            .subscribe(object : BaseObserver<HistoryTodayEntity>(activity, true) {
                override fun onSuccess(response: HistoryTodayEntity?) {
                    sendData("HistoryTodayViewModel", "s", response!!)
                }

                override fun onFailing(response: HistoryTodayEntity?) {
                    super.onFailing(response)
                    sendData("HistoryTodayViewModel", LiveDataEntity("fail", response?.msg))
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    sendData("HistoryTodayViewModel", LiveDataEntity("error", e.message))
                }

            })
    }

}