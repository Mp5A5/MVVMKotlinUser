package com.mp5a5.www.mvvmdemo.login

import android.util.ArrayMap
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @describe
 * @author ：mp5a5 on 2019/4/16 14：38
 * @email：wwb199055@126.com
 */
interface LoginApi {
  
  //1dbe64d0d4e5c700e98760644b234d56
  @GET("/mobile/get")
  fun getLogin(@QueryMap map: ArrayMap<String, @JvmSuppressWildcards Any>): Observable<LoginEntity>
}