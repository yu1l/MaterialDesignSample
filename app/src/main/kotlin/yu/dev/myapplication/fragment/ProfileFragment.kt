package yu.dev.myapplication.fragment

import android.animation.ValueAnimator
import android.app.Dialog
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_profile.*
import yu.dev.myapplication.R
import yu.dev.myapplication.activity.MainActivity
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfileFragment : BottomSheetDialogFragment() {

    private val TAG = ProfileFragment::class.simpleName

    private var scaled = false
    private var originalProfileImageHeight = 0
    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @NonNull
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // style.xmlに作ったStyleをセットする
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetDialog)
        return super.onCreateDialog(savedInstanceState)
    }

    private fun scaleProfileImage() {
        val anim = ValueAnimator.ofInt(originalProfileImageHeight, originalProfileImageHeight*2)
        anim.addUpdateListener {
            val animatedValue = it.animatedValue as Int
            val layoutParams = profile_image.layoutParams
            layoutParams.height = animatedValue
            profile_image.layoutParams = layoutParams
        }
        anim.duration = 300
        anim.start()
    }

    private fun shrinkProfileImage() {
        val anim = ValueAnimator.ofInt(originalProfileImageHeight*2, originalProfileImageHeight)
        anim.addUpdateListener {
            val animatedValue = it.animatedValue as Int
            val layoutParams = profile_image.layoutParams
            layoutParams.height = animatedValue
            profile_image.layoutParams = layoutParams
        }
        anim.duration = 300
        anim.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        originalProfileImageHeight = profile_image.layoutParams.height

        disposable.add(
                (activity as MainActivity).subject
                        .sample(1000, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            shrinkProfileImage()
                            scaled = false
                            Log.e(TAG, "hidden called")
                        })

        profile_image.setOnClickListener {
            if (!scaled) {
                scaleProfileImage()
            } else {
                shrinkProfileImage()
            }
            scaled = !scaled
        }
    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String) : ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            args.putString(ProfileFragment.ARG_PARAM1, param1)
            args.putString(ProfileFragment.ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
