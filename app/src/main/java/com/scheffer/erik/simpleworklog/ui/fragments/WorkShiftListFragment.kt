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
import com.scheffer.erik.simpleworklog.database.entities.WorkShift
import com.scheffer.erik.simpleworklog.ui.recyclerviewadapters.WorkShiftRecyclerViewAdapter
import com.scheffer.erik.simpleworklog.utils.observeOnce
import com.scheffer.erik.simpleworklog.viewmodels.WorkShiftListViewModel
import kotlinx.android.synthetic.main.fragment_workshift_list.view.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.OffsetDateTime

/**
 * A fragment representing a list of Work Shifts.
 */
class WorkShiftListFragment : Fragment() {

    private val workShiftListViewModel by viewModel<WorkShiftListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_workshift_list, container, false)

        // Set the adapter
        val workShiftAdapter = WorkShiftRecyclerViewAdapter()
        with(view.work_shift_list) {
            adapter = workShiftAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        workShiftListViewModel.allWorkShifts.observe(this, Observer { workLogs ->
            workLogs?.let { workShiftAdapter.setWorkShifts(it) }
        })

        view.work_shift_fab.inflate(R.menu.work_shift_fab_menu)
        view.work_shift_fab.setOnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.action_fast_clock_in -> {
                    workShiftListViewModel.getOpenWorkShift().observeOnce(this, Observer {
                        if (it != null) {
                            activity?.toast(R.string.work_shift_only_one_open_allowed)
                        } else {
                            workShiftListViewModel.persist(WorkShift(enterTime = OffsetDateTime.now()))
                        }
                    })
                }
                R.id.action_fast_clock_out ->
                    workShiftListViewModel.getOpenWorkShift().observeOnce(this, Observer {
                        if (it != null) {
                            workShiftListViewModel.persist(it.apply { exitTime = OffsetDateTime.now() })
                        } else {
                            activity?.toast(R.string.work_shift_open_shift_not_found)
                        }
                    })
//                    workShiftListViewModel.persist(
//                            WorkLog(registerTime = Calendar.getInstance(),
//                                    registerType = RegisterType.CLOCK_OUT))
//                R.id.action_new_work_log -> activity?.startActivity<WorkLogEditActivity>()
            }

            false
        }

        return view
    }
}
