package yu.dev.myapplication.fragment

import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_blank.*

import yu.dev.myapplication.R

/**
 * A simple [Fragment] subclass.
 *
 */
class BlankFragment : Fragment() {

    companion object {
        private val mBackgroundColor: String = "background_color"
        fun newInstance(@ColorRes IdRes: Int): BlankFragment {
            val frag = BlankFragment()
            val b = Bundle()
            b.putInt(mBackgroundColor, IdRes)
            frag.arguments = b
            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blank_frame.setBackgroundResource(arguments!!.getInt(mBackgroundColor))
    }
}
