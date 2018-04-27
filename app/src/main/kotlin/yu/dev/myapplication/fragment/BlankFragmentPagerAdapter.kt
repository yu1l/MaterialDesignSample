package yu.dev.myapplication.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class BlankFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return BlankFragment.newInstance(android.R.color.holo_blue_bright)
            1 -> return BlankFragment.newInstance(android.R.color.holo_green_light)
            2 -> return BlankFragment.newInstance(android.R.color.holo_red_dark)
        }
        return null
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "ページ" + (position + 1)
    }
}