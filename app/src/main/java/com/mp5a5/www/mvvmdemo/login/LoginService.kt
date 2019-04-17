package com.mp5a5.www.mvvmdemo.login

import android.util.ArrayMap
import com.mp5a5.www.library.use.RetrofitFactory
import io.reactivex.Observable

/**
 * @describe
 * @author ：mp5a5 on 2019/4/16 14：56
 * @email：wwb199055@126.com
 */
object LoginService {
  
  private val mLoginApi by lazy {
    RetrofitFactory.getInstance().create("http://apis.juhe.cn/", LoginApi::class.java)
  }
  
  fun getLoginData(key: String, phone: String): Observable<LoginEntity> {
    val map = ArrayMap<String, Any>()
    map["key"] = key
    map["phone"] = phone
    return mLoginApi.getLogin(map)
  }
  
}