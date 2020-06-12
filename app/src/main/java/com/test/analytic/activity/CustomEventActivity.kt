package com.test.analytic.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.test.analytic.R
import com.test.analytic.constants.AnalyticEvent
import com.test.analytic.constants.AnalyticKey
import com.test.analytic.constants.AnalyticProcess
import com.test.analytic.constants.AnalyticScreen
import kotlinx.android.synthetic.main.activity_custom_event.*

class CustomEventActivity : AppCompatActivity() {

    private var uid: String = ""
    private var analyticProcess: AnalyticProcess = AnalyticProcess()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_event)

        analyticProcess.setScreenName(this, AnalyticScreen.CUSTOM_EVENT)

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
        bundle.putString(AnalyticKey.PRIVILEGE_ID, "autoexcellence001")
        bundle.putString(AnalyticKey.PRIVILEGE_NAME, "รับส่วนลด 200 บาท ค่าอุปกรณ์เซนเซอร์หน้า-หลัง แบบไม่เจาะกันชน จากราคาปกติ 1,350 บาท")
        analyticProcess.logEvent(AnalyticEvent.SELECT_PRIVILEGE, bundle)
        Toast.makeText(this, "Send select privilege event with pr_id = autoexcellence001 to analytic.", Toast.LENGTH_LONG).show()
    }

    private fun logSelectPrivilegeEvent2() {
        val bundle = Bundle()
        bundle.putString(AnalyticKey.USER_ID, uid)
        bundle.putString(AnalyticKey.PRIVILEGE_ID, "yogurtland002")
        bundle.putString(AnalyticKey.PRIVILEGE_NAME, "รับส่วนลด 15% ทุกรสชาติ จาก Yogurtland")
        analyticProcess.logEvent(AnalyticEvent.SELECT_PRIVILEGE, bundle)
        Toast.makeText(this, "Send select privilege event with pr_id = yogurtland002 to analytic.", Toast.LENGTH_LONG).show()
    }

    private fun logRedeemPrivilegeEvent1() {
        val bundle = Bundle()
        bundle.putString(AnalyticKey.USER_ID, uid)
        bundle.putString(AnalyticKey.PRIVILEGE_ID, "autoexcellence001")
        bundle.putString(AnalyticKey.PRIVILEGE_TYPE, "USSD")
        bundle.putString(AnalyticKey.PRIVILEGE_CODE_VALUE, "*700*12345#")
        analyticProcess.logEvent(AnalyticEvent.CUSTOMER_REDEEM, bundle)
        Toast.makeText(this, "Send redeem code event with *700*12345# to analytic.", Toast.LENGTH_LONG).show()
    }

    private fun logRedeemPrivilegeEvent2() {
        val bundle = Bundle()
        bundle.putString(AnalyticKey.USER_ID, uid)
        bundle.putString(AnalyticKey.PRIVILEGE_ID, "yogurtland002")
        bundle.putString(AnalyticKey.PRIVILEGE_TYPE, "PROMO_CODE")
        bundle.putString(AnalyticKey.PRIVILEGE_CODE_VALUE, "KA3301772J5")
        analyticProcess.logEvent(AnalyticEvent.CUSTOMER_REDEEM, bundle)
        Toast.makeText(this, "Send redeem code event with KA3301772J5 to analytic.", Toast.LENGTH_LONG).show()
    }
}