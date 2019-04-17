package com.mp5a5.www.mvvmdemo

import java.util.regex.Pattern

/**
 * @describe
 * @author ：mp5a5 on 2019/4/16 15：29
 * @email：wwb199055@126.com
 */

/**
 * 数字和字母
 */
const val NUM_AND_ENGLISH = "^[0-9a-zA-Z]{6,20}\$"

/**
 * 电话号码
 */
const val PHONE_NUM = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}\$"

class RegexUtils {
  companion object {
    
    /**
     * @param patten 校验格式
     * @param str 校验内容
     */
    fun checkRegex(patten: String, str: String): Boolean {
      return Pattern.matches(patten, str)
    }
    
  }
  
}