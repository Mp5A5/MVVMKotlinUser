package com.mp5a5.www.mvvmdemo.login

import androidx.appcompat.app.AppCompatActivity
import com.mp5a5.www.library.use.BaseDisposableObserver
import com.mp5a5.www.library.utils.RxTransformerUtils
import com.mp5a5.www.mvvmdemo.mvvm.BaseRepository
import com.mp5a5.www.mvvmdemo.mvvm.LiveDataEntity

/**
 * @describe
 * @author ：mp5a5 on 2019/4/16 15：01
 * @email：wwb199055@126.com
 */
class LoginRepository : BaseRepository() {
  fun getLoginData(key: String, phone: String, activity: AppCompatActivity) {
    bindDisposable(LoginService
        .getLoginData(key, phone)
        .compose(RxTransformerUtils.observableTransformer())
        .subscribeWith(object : BaseDisposableObserver<LoginEntity>(activity, true) {
          override fun onSuccess(response: LoginEntity?) {
            sendData("Login", "sc", response!!)
          }
          
          override fun onFailing(response: LoginEntity?) {
            super.onFailing(response)
            sendData("Login", LiveDataEntity("fail", response?.msg))
          }
          
          override fun onError(e: Throwable) {
            super.onError(e)
            sendData("Login", LiveDataEntity("error", e.message))
          }
          
        }))
  }
}