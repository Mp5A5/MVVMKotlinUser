package com.mp5a5.www.mvvmdemo.history_today

import android.util.ArrayMap
import com.mp5a5.www.library.use.RetrofitFactory
import io.reactivex.Observable

/**
 * @describe
 * @author ：mp5a5 on 2019/4/15 10：29
 * @email：wwb199055@126.com
 */
object HistoryTodayService {

    private val mHistoryTodayApi by lazy {
        RetrofitFactory.getInstance().create("http://api.juheapi.com/", HistoryTodayApi::class.java)
    }


    fun getTodayEvents(key: String, v: String, month: Int, day: Int): Observable<HistoryTodayEntity> {
        val map = ArrayMap<String, Any>()
        map["key"] = key
        map["v"] = v
        map["month"] = month
        map["day"] = day
        return mHistoryTodayApi.getTodayEvents(map)
    }
}