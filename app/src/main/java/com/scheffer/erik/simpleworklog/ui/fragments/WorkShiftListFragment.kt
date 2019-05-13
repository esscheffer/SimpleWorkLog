package com.scheffer.erik.simpleworklog.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.ui.recyclerviewadapters.WorkShiftRecyclerViewAdapter
import com.scheffer.erik.simpleworklog.viewmodels.WorkShiftListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment representing a list of Work Shifts.
 */
class WorkShiftListFragment : Fragment() {

    private val workShiftListViewModel by viewModel<WorkShiftListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_workshift_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val adapter = WorkShiftRecyclerViewAdapter()
            view.adapter = adapter
            view.layoutManager = LinearLayoutManager(context)
            view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            workShiftListViewModel.allWorkShifts.observe(this, Observer { workShifts ->
                workShifts?.let { adapter.setWorkShifts(it) }
            })
        }
        return view
    }
}
