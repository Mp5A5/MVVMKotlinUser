package com.mp5a5.www.mvvmdemo.login

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.mp5a5.www.library.net.revert.BaseResponseEntity
import com.mp5a5.www.library.net.revert.OnBaseResponseListener
import com.mp5a5.www.mvvmdemo.NUM_AND_ENGLISH
import com.mp5a5.www.mvvmdemo.PHONE_NUM
import com.mp5a5.www.mvvmdemo.RegexUtils
import com.mp5a5.www.mvvmdemo.mvvm.BaseViewModel
import com.mp5a5.www.mvvmdemo.mvvm.LiveDataBus
import com.mp5a5.www.mvvmdemo.mvvm.LiveDataEntity

/**
 * @describe
 * @author ：mp5a5 on 2019/4/16 15：08
 * @email：wwb199055@126.com
 */
class LoginViewModel(application: Application) : BaseViewModel<LoginRepository>(application) {
  
  fun getLoginData(key: String, phone: String, passWord: String, activity: AppCompatActivity) {
    
    when {
      
      !RegexUtils.checkRegex(PHONE_NUM, phone) -> {
        LiveDataBus.getDefault().postEvent("LoginBtnCheck", null, "手机号输入有误")
        return
      }
      
      !RegexUtils.checkRegex(NUM_AND_ENGLISH, passWord) -> {
        LiveDataBus.getDefault().postEvent("LoginBtnCheck", null, "密码输入有误")
        return
      }
    }
    
    mRepository.getLoginData(key, phone, activity, object : OnBaseResponseListener<BaseResponseEntity<*>> {
      override fun onSuccess(response: BaseResponseEntity<*>?) {
        sendData("Login", "sc", response!! as LoginEntity)
      }
      
      override fun onFailing(response: BaseResponseEntity<*>?) {
        super.onFailing(response)
        sendData("Login", LiveDataEntity("fail", response?.msg))
      }
      
      override fun onError() {
        super.onError()
        sendData("Login", LiveDataEntity("error", "错误"))
      }
      
    })
  }
  
  
}