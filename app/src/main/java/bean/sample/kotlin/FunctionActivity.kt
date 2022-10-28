package bean.sample.kotlin

import android.util.Log
import bean.sample.kotlin.databinding.ActivitySpaceBinding
import priv.jb.base.basic.BaseActivity

class FunctionActivity : BaseActivity<ActivitySpaceBinding>() {
    override fun initAction() {
    }

    override fun initView() {
        multipleIntFunction(1, 2, 3, 5, 7)

        defaultParamsFunction(10)
    }

    fun multipleIntFunction(vararg ints: Int) {
        ints.forEach {
            Log.d(localClassName, "ints: $it")
        }
    }

    fun singleFunction() = 1
    fun defaultParamsFunction(param1: Int = 1, param2: Int = 2) {
        Log.d(localClassName, "param1: $param1, param2: $param2")
    }
}