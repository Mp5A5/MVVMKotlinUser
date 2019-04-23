package com.mp5a5.www.mvvmdemo.nba

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.mp5a5.www.library.net.revert.BaseResponseEntity
import com.mp5a5.www.library.net.revert.OnBaseResponseListener
import com.mp5a5.www.library.use.BaseDisposableSubscriber
import com.mp5a5.www.mvvmdemo.mvvm.BaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @describe
 * @author ：mp5a5 on 2019/4/9 17：41
 * @email：wwb199055@126.com
 */
class NBARepository : BaseRepository() {

    @SuppressLint("CheckResult")
    fun loadNBAData(key: String, appCompatActivity: AppCompatActivity, param: OnBaseResponseListener<BaseResponseEntity<*>>) {
        val subscribeWith = NbaService.getInstance().getNBAInfo1(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseDisposableSubscriber<NBAEntity>(appCompatActivity,true) {
                override fun onSuccess(response: NBAEntity?) {
                  param.onSuccess(response)
                }
            })

        bindDisposable(subscribeWith)
    }
}