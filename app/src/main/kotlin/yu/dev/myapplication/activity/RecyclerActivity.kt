package yu.dev.myapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import yu.dev.myapplication.R
import yu.dev.myapplication.RecyclerState
import yu.dev.myapplication.RecyclerType

class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val states = arrayListOf<RecyclerState>()

        // ヘッダー追加
        val headerState = RecyclerState(RecyclerType.HEADER, "ヘッダー")
        states.add(headerState)

        var sectionCount = 0

        for (i in 1..5) {

            // 2件目と3件目の上にセクションを追加
            if (i == 2 || i == 3) {
                sectionCount++
                val sectionState = RecyclerState(RecyclerType.SECTION, "セクション(区切り) No. $sectionCount")
                states.add(sectionState)
            }

            val state = RecyclerState(RecyclerType.BODY, "$i 件目")
            states.add(state)
        }

        // フッター追加
        val footerState = RecyclerState(RecyclerType.FOOTER, "フッター")
        states.add(footerState)

    }
}
