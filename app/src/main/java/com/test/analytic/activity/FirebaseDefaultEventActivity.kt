package com.test.analytic.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.test.analytic.R
import com.test.analytic.constants.AnalyticKey
import com.test.analytic.constants.AnalyticProcess
import com.test.analytic.constants.AnalyticScreen
import kotlinx.android.synthetic.main.activity_fb_default_event.*

class FirebaseDefaultEventActivity : AppCompatActivity() {

    private var uid: String = ""
    private var analyticProcess: AnalyticProcess = AnalyticProcess()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fb_default_event)

        analyticProcess.setScreenName(this, AnalyticScreen.FIREBASE_EVENT)

        val bundle :Bundle? = intent.extras
        if (bundle!=null){
            uid = bundle.getString(AnalyticKey.USER_ID)!!
            tvUserId.text = "($uid)"
            analyticProcess.setFirebaseUserId(uid)
        }

        initialEvent()
    }

    private fun initialEvent() {
        btnSelectPr1.setOnClickListener {
            logSelectPrivilegeEvent1()
        }

        btnRedeemPr1.setOnClickListener {
            logRedeemPrivilegeEvent1()
        }

        btnSelectPr2.setOnClickListener {
            logSelectPrivilegeEvent2()
        }

        btnRedeemPr2.setOnClickListener {
            logRedeemPrivilegeEvent2()
        }
    }

    private fun logSelectPrivilegeEvent1() {
        val bundle = Bundle()
        bundle.putString(AnalyticKey.USER_ID, uid)
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "autoexcellence001")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "รับส่วนลด 200 บาท ค่าอุปกรณ์เซนเซอร์หน้า-หลัง แบบไม่เจาะกันชน จากราคาปกติ 1,350 บาท")
        analyticProcess.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle)
        Toast.makeText(this, "Send select privilege event with pr_id = autoexcellence001 to analytic.", Toast.LENGTH_LONG).show()
    }

    private fun logSelectPrivilegeEvent2() {
        val bundle = Bundle()
        bundle.putString(AnalyticKey.USER_ID, uid)
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "yogurtland002")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "รับส่วนลด 15% ทุกรสชาติ จาก Yogurtland")
        analyticProcess.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle)
        Toast.makeText(this, "Send select privilege event with pr_id = yogurtland002 to analytic.", Toast.LENGTH_LONG).show()
    }

    private fun logRedeemPrivilegeEvent1() {
        val bundle = Bundle()
        bundle.putString(AnalyticKey.USER_ID, uid)
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "autoexcellence001")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "USSD")
        bundle.putString(FirebaseAnalytics.Param.VALUE, "*700*12345#")
        analyticProcess.logEvent(FirebaseAnalytics.Event.EARN_VIRTUAL_CURRENCY, bundle)
        Toast.makeText(this, "Send redeem code event with *700*12345# to analytic.", Toast.LENGTH_LONG).show()
    }

    private fun logRedeemPrivilegeEvent2() {
        val bundle = Bundle()
        bundle.putString(AnalyticKey.USER_ID, uid)
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "yogurtland002")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "PROMO_CODE")
        bundle.putString(FirebaseAnalytics.Param.VALUE, "KA3301772J5")
        analyticProcess.logEvent(FirebaseAnalytics.Event.EARN_VIRTUAL_CURRENCY, bundle)
        Toast.makeText(this, "Send redeem code event with KA3301772J5 to analytic.", Toast.LENGTH_LONG).show()
    }
}