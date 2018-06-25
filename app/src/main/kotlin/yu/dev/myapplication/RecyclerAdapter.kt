package yu.dev.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class RecyclerAdapter(private val context: Context, private val States: List<RecyclerState>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (RecyclerType.fromInt(viewType)) {
            RecyclerType.HEADER -> {
                // TODO
            }
        }
    }
}