package com.test.analytic

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.analytic.activity.CustomEventActivity
import com.test.analytic.activity.FirebaseDefaultEventActivity
import com.test.analytic.constants.AnalyticEvent
import com.test.analytic.constants.AnalyticKey
import com.test.analytic.constants.AnalyticProcess
import com.test.analytic.constants.AnalyticScreen
import it.sephiroth.android.library.xtooltip.Tooltip
import it.sephiroth.android.library.xtooltip.Typefaces
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var tooltip: Tooltip? = null

    var analyticProcess: AnalyticProcess = AnalyticProcess()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        analyticProcess.setScreenName(this, AnalyticScreen.MAIN)

        btnTestCustomEvent.setOnClickListener {
            if(!validateUserId()) {
                analyticProcess.setFirebaseUserId(edtUserId.text.toString())
                analyticProcess.logEventClick(AnalyticEvent.BTN_CUSTOM)
                val intent = Intent(this, CustomEventActivity::class.java)
                intent.putExtra(AnalyticKey.USER_ID, edtUserId.text.toString())
                startActivity(intent)
            }
        }

        btnTestFirebaseEvent.setOnClickListener {
            if(!validateUserId()) {
                analyticProcess.setFirebaseUserId(edtUserId.text.toString())
                analyticProcess.logEventClick(AnalyticEvent.BTN_FIREBASE_DEFAULT)
                val intent = Intent(this, FirebaseDefaultEventActivity::class.java)
                intent.putExtra(AnalyticKey.USER_ID, edtUserId.text.toString())
                startActivity(intent)
            }
        }
    }

    private fun validateUserId(): Boolean{

        if (edtUserId.text.isNullOrEmpty()) {
            val typeface = Typefaces[this, "fonts/GillSans.ttc"]
            val metrics = resources.displayMetrics

            tooltip?.dismiss()

            tooltip = Tooltip.Builder(this)
                .anchor(edtUserId, 0, 0, false)
                .text("ID not empty.")
                .styleId(R.style.ToolTipAltStyle)
                .typeface(typeface)
                .maxWidth(metrics.widthPixels / 2)
                .arrow(true)
                .floatingAnimation(Tooltip.Animation.DEFAULT)
                .showDuration(2000)
                .overlay(true)
                .create()

            tooltip
                ?.doOnHidden {
                    tooltip = null
                }
                ?.doOnFailure { }
                ?.doOnShown { }
                ?.show(edtUserId, Tooltip.Gravity.RIGHT, true)
            return true
        } else {
            return false
        }
    }
}
