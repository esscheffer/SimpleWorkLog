package com.scheffer.erik.simpleworklog.ui.recyclerviewadapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.database.entities.WorkShift
import com.scheffer.erik.simpleworklog.utils.getDefaultDateString
import com.scheffer.erik.simpleworklog.utils.getDefaultTimeString
import kotlinx.android.synthetic.main.workshift_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [WorkShift]..
 */
class WorkShiftRecyclerViewAdapter(
        private var workShifts: List<WorkShift> = emptyList())
    : RecyclerView.Adapter<WorkShiftRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.workshift_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workShift = workShifts[position]
        with(holder) {
            enterDateTextView.text = workShift.enterTime.getDefaultDateString()
            enterHourTextView.text = workShift.enterTime.getDefaultTimeString()
            val exitTime = workShift.exitTime
            if (exitTime != null) {
                exitDateTextView.text = exitTime.getDefaultDateString()
                exitHourTextView.visibility = View.VISIBLE
                exitHourTextView.text = exitTime.getDefaultTimeString()
            } else {
                exitDateTextView.text = holder.itemView.context.getString(R.string.on_going)
                exitHourTextView.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = workShifts.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val enterDateTextView: TextView = view.enter_date_text
        val enterHourTextView: TextView = view.enter_hour_text
        val exitDateTextView: TextView = view.exit_date_text
        val exitHourTextView: TextView = view.exit_hour_text

        override fun toString(): String {
            return "ViewHolder(enterDateTextView=$enterDateTextView, enterHourTextView=$enterHourTextView, exitDateTextView=$exitDateTextView, exitHourTextView=$exitHourTextView)"
        }
    }

    fun setWorkShifts(workShifts: List<WorkShift>) {
        this.workShifts = workShifts
        notifyDataSetChanged()
    }
}
