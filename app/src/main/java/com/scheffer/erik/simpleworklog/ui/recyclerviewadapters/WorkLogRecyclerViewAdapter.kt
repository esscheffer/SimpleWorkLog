package com.scheffer.erik.simpleworklog.ui.recyclerviewadapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.RegisterType
import com.scheffer.erik.simpleworklog.database.entities.WorkLog
import com.scheffer.erik.simpleworklog.ui.activities.ARG_WORK_LOG_ID
import com.scheffer.erik.simpleworklog.ui.activities.WorkLogEditActivity
import com.scheffer.erik.simpleworklog.utils.getDefaultDateString
import com.scheffer.erik.simpleworklog.utils.getDefaultTimeString
import kotlinx.android.synthetic.main.worklog_item.view.*
import org.jetbrains.anko.startActivity

/**
 * [RecyclerView.Adapter] that can display a [WorkLog].
 */
class WorkLogRecyclerViewAdapter(
        private var workLogs: List<WorkLog> = emptyList())
    : RecyclerView.Adapter<WorkLogRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val workLog = v.tag as WorkLog
            v.context.startActivity<WorkLogEditActivity>(ARG_WORK_LOG_ID to workLog.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.worklog_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workLog = workLogs[position]
        holder.registerDateText.text = workLog.registerTime.getDefaultDateString()
        holder.registerHourText.text = workLog.registerTime.getDefaultTimeString()
        holder.registerTypeText.text = when (workLog.registerType) {
            RegisterType.CLOCK_IN -> holder.itemView.context.getString(R.string.clock_in)
            RegisterType.CLOCK_OUT -> holder.itemView.context.getString(R.string.clock_out)
        }

        with(holder.view) {
            tag = workLog
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = workLogs.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val registerDateText: TextView = view.register_date_text
        val registerHourText: TextView = view.register_hour_text
        val registerTypeText: TextView = view.register_type_text

        override fun toString(): String {
            return "ViewHolder(view=$view, registerDateText=$registerDateText, registerHourText=$registerHourText, registerTypeText=$registerTypeText)"
        }
    }

    fun setWorkLogs(workLogs: List<WorkLog>) {
        this.workLogs = workLogs
        notifyDataSetChanged()
    }
}
