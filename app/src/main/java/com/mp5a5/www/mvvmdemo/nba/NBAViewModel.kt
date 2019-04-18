package com.mp5a5.www.mvvmdemo.nba

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.mp5a5.www.mvvmdemo.mvvm.BaseViewModel

/**
 * @describe
 * @author ：mp5a5 on 2019/4/9 17：25
 * @email：wwb199055@126.com
 */
class NBAViewModel(application: Application) : BaseViewModel<NBARepository>(application) {
  
  fun getNBAData(key: String, activity: AppCompatActivity) {
    mRepository.loadNBAData(key, activity)
  }
  
}