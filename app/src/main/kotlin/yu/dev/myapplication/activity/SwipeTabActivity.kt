package yu.dev.myapplication.activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.activity_swipe_tab.*
import yu.dev.myapplication.R
import yu.dev.myapplication.fragment.BlankFragmentPagerAdapter

class SwipeTabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_tab)
        setViews()
    }

    private fun setViews() {
        val manager = supportFragmentManager
        val adapter = BlankFragmentPagerAdapter(manager)
        viewPager.adapter = adapter
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        tabLayout.setupWithViewPager(viewPager)
    }
}