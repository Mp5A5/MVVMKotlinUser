package com.mp5a5.www.mvvmdemo.login

import androidx.appcompat.app.AppCompatActivity
import com.mp5a5.www.library.net.revert.BaseResponseEntity
import com.mp5a5.www.library.net.revert.OnBaseResponseListener
import com.mp5a5.www.library.use.BaseDisposableObserver
import com.mp5a5.www.library.utils.RxTransformerUtils
import com.mp5a5.www.mvvmdemo.mvvm.BaseRepository

/**
 * @describe
 * @author ：mp5a5 on 2019/4/16 15：01
 * @email：wwb199055@126.com
 */
class LoginRepository : BaseRepository() {
  fun getLoginData(
      key: String,
      phone: String,
      activity: AppCompatActivity,
      listener: OnBaseResponseListener<BaseResponseEntity<*>>
  ) {
    bindDisposable(
        LoginService
            .getLoginData(key, phone)
            .compose(RxTransformerUtils.observableTransformer())
            .subscribeWith(object : BaseDisposableObserver<LoginEntity>(activity, true) {
              override fun onSuccess(response: LoginEntity?) {
                listener.onSuccess(response)
              }
              
              override fun onFailing(response: LoginEntity?) {
                super.onFailing(response)
                listener.onFailing(response)
              }
              
              override fun onError(e: Throwable) {
                super.onError(e)
                listener.onError()
              }
              
            })
    )
  }
}