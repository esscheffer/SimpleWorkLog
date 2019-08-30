package com.scheffer.erik.simpleworklog.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.ui.fragments.WorkLogListFragment
import com.scheffer.erik.simpleworklog.ui.fragments.WorkShiftListFragment

class MainPageAdapter(fm: FragmentManager, private val context: Context)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> WorkLogListFragment()
            else -> return WorkShiftListFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.work_log_title)
            else -> context.getString(R.string.work_shift_title)
        }
    }
}