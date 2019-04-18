package com.mp5a5.www.mvvmdemo.login

import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.mp5a5.www.mvvmdemo.R
import com.mp5a5.www.mvvmdemo.Tools
import com.mp5a5.www.mvvmdemo.mvvm.BaseMVVMActivity
import com.mp5a5.www.mvvmdemo.mvvm.LiveDataBus
import com.mp5a5.www.mvvmdemo.mvvm.LiveDataEntity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

/**
 * @describe
 * @author ：mp5a5 on 2019/4/16 10：23
 * @email：wwb199055@126.com
 */
class LoginActivity : BaseMVVMActivity<LoginViewModel>(), View.OnClickListener {
  
  
  override fun dataObserver() {
    
    LiveDataBus.getDefault().subscribe<String>("LoginBtnCheck").observe(this, Observer { msg ->
      msg?.let {
        toast(it)
      }
    })
    
    LiveDataBus.getDefault().subscribe<LoginEntity>("Login", "sc").observe(this, Observer { msg ->
      msg?.let {
        toast(it.msg)
        Log.e("-->",it.toString())
      }
    })
    
    LiveDataBus.getDefault().subscribe<LiveDataEntity>("Login").observe(this, Observer { msg ->
      msg?.let {
        toast(it.msg)
      }
    })
  }
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
    
    btnLogin.setOnClickListener(this)
    ivNameClear.setOnClickListener(this)
    ivPasswordClear.setOnClickListener(this)
  }
  
  override fun onClick(v: View?) {
    v?.id?.let {
      when (it) {
        R.id.btnLogin -> mViewModel.getLoginData("1dbe64d0d4e5c700e98760644b234d56", Tools.getEditTextString(etInputName), Tools.getEditTextString(etInputPassword), this)
        R.id.ivNameClear -> etInputName.setText("")
        R.id.ivPasswordClear -> switchPasswordCleartext()
      }
    }
  }
  
  private fun switchPasswordCleartext() {
    val tag = if (etInputPassword.tag == null) {
      false
    } else {
      etInputPassword.tag as Boolean
    }
    val txt = etInputPassword.text.toString().trim()
    var length = 0
    if (!TextUtils.isEmpty(txt)) {
      length = txt.length
    }
    if (tag) {
      ivPasswordClear.setImageResource(R.mipmap.show_psw_press)
      etInputPassword.tag = false
      etInputPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    } else {
      ivPasswordClear.setImageResource(R.mipmap.show_psw)
      etInputPassword.tag = true
      etInputPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    }
    etInputPassword.setSelection(length)
  }
  
  
}