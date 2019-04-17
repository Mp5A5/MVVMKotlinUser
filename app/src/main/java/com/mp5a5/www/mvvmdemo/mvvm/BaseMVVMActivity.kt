package com.mp5a5.www.mvvmdemo.mvvm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.annotation.NonNull
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import java.lang.reflect.ParameterizedType

/**
 * @describe
 * @author ：mp5a5 on 2019/4/9 16：48
 * @email：wwb199055@126.com
 */
abstract class BaseMVVMActivity<T : BaseViewModel<*>> : RxAppCompatActivity() {
  
  protected lateinit var mViewModel: T
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mViewModel = providers((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>)
    dataObserver()
  }
  
  abstract fun dataObserver()
  
  private fun <T : ViewModel> providers(@NonNull modelClazz: Class<T>): T {
    val instance = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    return instance.create(modelClazz)
  }
  
  override fun onDestroy() {
    super.onDestroy()
    LiveDataBus.getDefault().clear()
  }
  
  
}

