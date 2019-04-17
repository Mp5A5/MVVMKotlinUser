package com.mp5a5.www.mvvmdemo.mvvm

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @describe
 * @author ：mp5a5 on 2019/4/9 14：50
 * @email：wwb199055@126.com
 */
open class BaseRepository {

    private var mCompositeDisposable: CompositeDisposable? = null

    protected fun bindDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(disposable)
    }

    fun unbindDisposable() {
        if (mCompositeDisposable != null && mCompositeDisposable?.isDisposed!!) {
            mCompositeDisposable?.clear()
        }
    }

    protected fun sendData(eventKey: Any, value: Any) {
        sendData(eventKey, null, value)
    }

    protected fun sendData(eventKey: Any, tag: String?, value: Any) {
        LiveDataBus.getDefault().postEvent(eventKey, tag, value)
    }


}