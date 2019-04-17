package com.mp5a5.www.mvvmdemo.history_today

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.mp5a5.www.mvvmdemo.R

/**
 * @describe
 * @author ：mp5a5 on 2019/4/15 10：50
 * @email：wwb199055@126.com
 */
class HistoryTodayAdapter(private val dataList: MutableList<HistoryTodayEntity.ResultEntity>?) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder?
        var mView: View? = convertView
        if (mView == null) {
            mView = View.inflate(parent?.context, R.layout.item_history_tody, null)
            viewHolder = ViewHolder(mView)
            mView.tag = viewHolder
        } else {
            mView = convertView
            viewHolder = mView?.tag as ViewHolder
        }

        viewHolder.txt.text = dataList!![position].title

        return mView!!
    }

    override fun getItem(position: Int): Any = this.dataList!![position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = /*if (this.dataList.isNotEmpty()) dataList.size else 0*/ this.dataList?.size ?: 0

    inner class ViewHolder(var view: View) {
        var txt: TextView = view.findViewById(R.id.tvText)
    }
}