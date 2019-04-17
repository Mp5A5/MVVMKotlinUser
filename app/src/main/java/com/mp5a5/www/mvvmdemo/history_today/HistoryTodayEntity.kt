package com.mp5a5.www.mvvmdemo.history_today

import com.google.gson.annotations.SerializedName
import com.mp5a5.www.library.net.revert.BaseResponseEntity

/**
 * @describe
 * @author ：mp5a5 on 2019/4/16 19：04
 * @email：wwb199055@126.com
 */
data class HistoryTodayEntity(
    @SerializedName("error_code")
    override  var code: Int,
    @SerializedName("reason")
    override var msg: String, var result: MutableList<ResultEntity>?
) : BaseResponseEntity<HistoryTodayEntity>() {

    data class ResultEntity(
        var _id: String?,
        var title: String?,
        var pic: String?,
        var year: Int,
        var month: Int,
        var day: Int,
        var des: String?,
        var lunar: String?
    )

}