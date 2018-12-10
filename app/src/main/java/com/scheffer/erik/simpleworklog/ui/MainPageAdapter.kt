package com.scheffer.erik.simpleworklog.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.scheffer.erik.simpleworklog.R
import com.scheffer.erik.simpleworklog.ui.fragments.WorkLogListFragment
import com.scheffer.erik.simpleworklog.ui.fragments.WorkShiftFragment

class MainPageAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> WorkLogListFragment()
            else -> return WorkShiftFragment()
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