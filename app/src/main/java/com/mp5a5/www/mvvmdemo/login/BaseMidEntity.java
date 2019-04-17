package com.mp5a5.www.mvvmdemo.login;

import com.google.gson.annotations.SerializedName;

import com.mp5a5.www.library.net.revert.BaseResponseEntity;
import com.mp5a5.www.library.utils.ApiConfig;
import org.jetbrains.annotations.NotNull;

/**
 * @author ：mp5a5 on 2019/4/16 14：51
 * @describe
 * @email：wwb199055@126.com
 */
class BaseMidEntity<T> extends BaseResponseEntity<T> {

  @SerializedName("reason")
  private String msg;
  @SerializedName(value = "error_code", alternate = {"resultcode"})
  private int code;

  @NotNull
  @Override
  public String getMsg() {
    return msg;
  }

  @Override
  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Override
  public boolean success() {
    return ApiConfig.getSucceedCode() == code;
  }

  @Override
  public boolean tokenInvalid() {
    return ApiConfig.getInvalidateToken() == code;
  }


}
