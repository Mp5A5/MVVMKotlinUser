package com.mp5a5.www.mvvmdemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * @author ：mp5a5 on 2019/4/16 15：19
 * @describe
 * @email：wwb199055@126.com
 */
public class Tools {

  /**
   * 获取手机制造商
   */
  public static String getVendor() {
    return Build.BRAND;
  }

  /**
   * 获取系统版本
   */
  public static String getBuildVERSION() {
    return Build.VERSION.RELEASE;
  }


  /**
   * 自己生成UUID
   */
  public static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  /**
   * 获取版本名称
   */
  public static String getVersionName(Context context) {
    String versionName = "0";
    try {
      PackageInfo info = context.getPackageManager().getPackageInfo(
          context.getPackageName(), 0);
      versionName = info.versionName;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return versionName;
  }

  /**
   * 获取版本名称
   */
  public static int getVersionCode(Context context) {
    int versionCode = 0;
    try {
      PackageInfo info = context.getPackageManager().getPackageInfo(
          context.getPackageName(), 0);
      versionCode = info.versionCode;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }

    return versionCode;
  }


  public static String getEditTextString(EditText mEditext) {
    return mEditext.getText().toString().trim();
  }

  public static String getString(TextView tv) {
    return tv.getText().toString().trim();
  }


  public static boolean isEmpty(TextView tv) {
    return TextUtils.isEmpty(getString(tv));
  }


  private static long lastClickTime = 0;

  /**
   * 返回true 可以点击
   */
  public static boolean isFastDoubleClick() {
    long time = System.currentTimeMillis();
    long nowTime = time - lastClickTime;
    lastClickTime = time;
    return nowTime > 2000;
  }

}
