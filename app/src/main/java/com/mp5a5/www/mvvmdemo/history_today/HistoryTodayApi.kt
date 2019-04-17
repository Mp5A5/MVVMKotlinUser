package com.mp5a5.www.mvvmdemo.history_today

import android.util.ArrayMap
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * @describe
 * @author ：mp5a5 on 2019/4/15 10：19
 * @email：wwb199055@126.com
 */
interface HistoryTodayApi {

    /**
     * key:cd80790e2a20c97e793c7ed8acecfac6
     */
    @POST("japi/toh")
    fun getTodayEvents(@QueryMap map: ArrayMap<String, @JvmSuppressWildcards Any>): Observable<HistoryTodayEntity>
}