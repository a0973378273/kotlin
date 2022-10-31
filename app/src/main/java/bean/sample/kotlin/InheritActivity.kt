package bean.sample.kotlin

import android.app.Activity
import android.os.Bundle
import android.util.Log

open class InheritActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_space)
    }

    open fun inheritFunction() = "inherit"

    class SubClass : InheritActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            Log.d("inheritFunction", "${inheritFunction()}")
        }

        override fun inheritFunction(): String = "subFunction"
    }
}