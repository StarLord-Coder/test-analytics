package com.test.analytic.constants

import android.app.Activity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class AnalyticProcess(){

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    var userId: String = ""

    init {
        initFirebase()

    }

    fun initFirebase() {
        firebaseAnalytics = Firebase.analytics
    }

    fun setFirebaseUserId(userId: String) {
        this.userId = userId
        firebaseAnalytics.setUserId(this.userId)
    }

    fun setScreenName(activity: Activity, screenName: AnalyticScreen) {
        firebaseAnalytics.setCurrentScreen(activity, screenName.screen, null)
    }

    fun logEventClick(buttonName: String) {
        val bundle = Bundle()
        bundle.putString(AnalyticKey.USER_ID, userId)
        bundle.putString(AnalyticKey.BTN_NAME, buttonName)
        firebaseAnalytics.logEvent(AnalyticEvent.CLICK_BUTTON, bundle)
    }

    fun logCustomEvent(eventName: String, bundle: Bundle) {
//        val bundle = Bundle()
//        bundle.putString(AnalyticKey.USER_ID, userId)
//        bundle.putString(AnalyticKey.PRIVILEGE_ID, "buttonName")
//        bundle.putString(AnalyticKey.PRIVILEGE_NAME, "buttonName")
        firebaseAnalytics.logEvent(eventName, bundle)
    }
}