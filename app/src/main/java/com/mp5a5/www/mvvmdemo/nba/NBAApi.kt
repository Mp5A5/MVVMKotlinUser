package com.mp5a5.www.mvvmdemo.nba

import android.util.ArrayMap
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @describe
 * @author ：mp5a5 on 2019/4/9 17：33
 * @email：wwb199055@126.com
 */
interface NBAApi {
    @GET("onebox/basketball/nba")
    fun getNBAInfo(@QueryMap map: ArrayMap<String, @JvmSuppressWildcards Any>): Observable<NBAEntity>

    @GET("onebox/basketball/nba")
    fun getNBAInfo1(@QueryMap map: ArrayMap<String, @JvmSuppressWildcards Any>): Flowable<NBAEntity>
}