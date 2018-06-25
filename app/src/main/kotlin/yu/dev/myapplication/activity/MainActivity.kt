package yu.dev.myapplication.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.transition.Slide
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import yu.dev.myapplication.R
import yu.dev.myapplication.R.id.recycler_button
import yu.dev.myapplication.fragment.ItemListDialogFragment
import yu.dev.myapplication.fragment.PlusOneFragment
import yu.dev.myapplication.fragment.ProfileFragment
import yu.dev.myapplication.module.AnimationModule
import yu.dev.myapplication.module.GlideApp
import yu.dev.myapplication.module.SettingList

class MainActivity : AppCompatActivity(), PlusOneFragment.OnFragmentInteractionListener, ItemListDialogFragment.Listener {

    private val TAG = MainActivity::class.simpleName
    val subject = PublishSubject.create<Boolean>()
    private var sheetState = BottomSheetBehavior.STATE_HIDDEN

    override fun onFragmentInteraction(uri: Uri) {
        Log.e(TAG, "onFragmentInteraction")
    }

    override fun onItemClicked(position: Int) {
        Log.e(TAG, "clicked:" + position.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activity: MainActivity = this
//        activity.findViewById<View>(R.id.mainLayout).setBackgroundColor(Color.rgb(16,114,157))
        activity.findViewById<View>(R.id.mainLayout).setBackgroundColor(Color.WHITE)

        button.setOnClickListener {
            showPlus(savedInstanceState)
        }

//        bottomSheet.setOnClickListener {
//            showBottomSheet()
//        }

        button2.setOnClickListener {
            signIn()
        }
        button3.setOnClickListener {
            swipeTab()
        }
        twitter.setOnClickListener {
            signInTwitter()
        }
        recycler_button.setOnClickListener {
            showRecycler()
        }

        GlideApp.with(this)
                .load("http://goo.gl/gEgYUd")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView)

        // add material design transition
//        val behavior = BottomSheetBehavior.from(bottom_sheet)
//        behavior.peekHeight = 0
        profile_button.setOnClickListener {
            val profileFrag = ProfileFragment.newInstance("", "")
            profileFrag.show(supportFragmentManager, profileFrag.tag)

//            behavior.state = BottomSheetBehavior.STATE_EXPANDED
//            this.sheetState = behavior.state
        }
//        behavior.state = BottomSheetBehavior.STATE_HIDDEN
//        behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                when (newState) {
//                    BottomSheetBehavior.STATE_DRAGGING -> {
//                    }
//                    BottomSheetBehavior.STATE_SETTLING -> {
//                    }
//                    BottomSheetBehavior.STATE_EXPANDED -> {
//                    }
//                    BottomSheetBehavior.STATE_COLLAPSED -> {
//                    }
//                    BottomSheetBehavior.STATE_HIDDEN -> {
//                        sheetState = BottomSheetBehavior.STATE_HIDDEN
//                        subject.onNext(true)
//                    }
//                }
//            }
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
//        })

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.allowEnterTransitionOverlap = false
            val slide = Slide(Gravity.RIGHT)
        }
    }

    private fun showRecycler() {
        intent = Intent(this, RecyclerActivity::class.java)
        startActivity(intent)
    }

    private fun showPlus(savedInstanceState: Bundle?) {
//        if (savedInstanceState == null) {
            val plusOne = PlusOneFragment()
            val transaction = supportFragmentManager.beginTransaction()
            plusOne.allowEnterTransitionOverlap
            plusOne.allowReturnTransitionOverlap
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_out, android.R.anim.fade_out)
//            transaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_enter, R.anim.fragment_exit)
            transaction.addToBackStack("PlusFragment")
//            transaction.add(R.id.mainLayout, plusOne)
            transaction.add(R.id.container, plusOne)
            transaction.commit()
//        }
    }

    private fun showBottomSheet() {
        val bottomSheet = ItemListDialogFragment.newInstance(4, SettingList)
        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
    }

    private fun signIn() {
        intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("Type", AnimationModule.Type.STANDARD)
        startActivity(intent)
    }

    private fun signInTwitter() {
        intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("Type", AnimationModule.Type.TWITTER)
        startActivity(intent)
    }

    private fun swipeTab() {
        intent = Intent(this, SwipeTabActivity::class.java)
        startActivity(intent)
    }
}