package com.mp5a5.www.mvvmdemo.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.lang.reflect.ParameterizedType

/**
 * @describe
 * @author ：mp5a5 on 2019/4/9 15：04
 * @email：wwb199055@126.com
 */
open class BaseViewModel<T : BaseRepository>(application: Application) : AndroidViewModel(application) {

    protected val mRepository: T by lazy {
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>).newInstance()
    }

    override fun onCleared() {
        super.onCleared()
        mRepository.unbindDisposable()
    }
    
    protected fun sendData(eventKey: Any, value: Any) {
        sendData(eventKey, null, value)
    }
    
    protected fun sendData(eventKey: Any, tag: String?, value: Any) {
        LiveDataBus.getDefault().postEvent(eventKey, tag, value)
    }
    
}