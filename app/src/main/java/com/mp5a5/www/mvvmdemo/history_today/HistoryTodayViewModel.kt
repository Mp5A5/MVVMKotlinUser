package com.mp5a5.www.mvvmdemo.history_today

import android.app.Application
import com.mp5a5.www.library.net.revert.BaseResponseEntity
import com.mp5a5.www.library.net.revert.OnBaseResponseListener
import com.mp5a5.www.mvvmdemo.mvvm.BaseViewModel
import com.mp5a5.www.mvvmdemo.mvvm.LiveDataEntity
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity

/**
 * @describe
 * @author ：mp5a5 on 2019/4/15 13：34
 * @email：wwb199055@126.com
 */
class HistoryTodayViewModel(application: Application) : BaseViewModel<HistoryTodayRepository>(application) {
  
  fun getTodayResult(key: String, v: String, month: Int, day: Int, activity: RxAppCompatActivity) {
    mRepository.getTodayResult(key, v, month, day, activity, object : OnBaseResponseListener<BaseResponseEntity<*>> {
      override fun onSuccess(response: BaseResponseEntity<*>?) {
        sendData("HistoryTodayViewModel", "s", response!!)
      }
      
      override fun onFailing(response: BaseResponseEntity<*>?) {
        super.onFailing(response)
        sendData("HistoryTodayViewModel", LiveDataEntity("fail", response?.msg))
      }
      
      override fun onError() {
        super.onError()
        sendData("HistoryTodayViewModel", LiveDataEntity("error", "错误"))
      }
    })
  }
}