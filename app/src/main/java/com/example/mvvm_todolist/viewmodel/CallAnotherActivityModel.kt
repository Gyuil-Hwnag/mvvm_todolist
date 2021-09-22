package com.example.mvvm_todolist.viewmodel

class CallAnotherActivityModel(private val navigator: CallAnotherActivityNavigator){
    fun onCreate() {}
    fun onResume() {}
    fun onPause() {}
    fun onDestroy() {}
    fun callActivity() {
        navigator.callActivity()
    }
}