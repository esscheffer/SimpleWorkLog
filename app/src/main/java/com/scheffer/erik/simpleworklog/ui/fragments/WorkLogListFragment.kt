package com.scheffer.erik.simpleworklog.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.RegisterType
import com.scheffer.erik.simpleworklog.database.entities.WorkLog
import com.scheffer.erik.simpleworklog.ui.activities.WorkLogEditActivity
import com.scheffer.erik.simpleworklog.ui.recyclerviewadapters.WorkLogRecyclerViewAdapter
import com.scheffer.erik.simpleworklog.viewmodels.WorkLogListViewModel
import kotlinx.android.synthetic.main.fragment_worklog_list.view.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.OffsetDateTime

/**
 * A fragment representing a list of Work Logs.
 */
class WorkLogListFragment : Fragment() {

    private val workLogListViewModel by viewModel<WorkLogListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_worklog_list, container, false)

        // Set the adapter
        val workLogAdapter = WorkLogRecyclerViewAdapter()
        with(view.work_log_list) {
            adapter = workLogAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        workLogListViewModel.allWorkLogs.observe(this, Observer { workLogs ->
            workLogs?.let { workLogAdapter.setWorkLogs(it) }
        })

        view.work_log_fab.inflate(R.menu.work_log_fab_menu)
        view.work_log_fab.setOnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.action_fast_clock_in ->
                    workLogListViewModel.persist(
                            WorkLog(registerTime = OffsetDateTime.now(),
                                    registerType = RegisterType.CLOCK_IN))
                R.id.action_fast_clock_out ->
                    workLogListViewModel.persist(
                            WorkLog(registerTime = OffsetDateTime.now(),
                                    registerType = RegisterType.CLOCK_OUT))
                R.id.action_new_work_log -> activity?.startActivity<WorkLogEditActivity>()
            }

            false
        }

        return view
    }
}
