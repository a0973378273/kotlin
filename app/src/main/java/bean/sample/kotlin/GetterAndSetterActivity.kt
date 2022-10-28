package bean.sample.kotlin

import bean.sample.kotlin.databinding.ActivitySpaceBinding
import priv.jb.base.basic.BaseActivity

class GetterAndSetterActivity : BaseActivity<ActivitySpaceBinding>() {
    //唯讀
    val name: String
        get() = "Bill"

    val name1: String
        get() {
            return "Alex"
        }

    //讀寫
    var setValue = 1
    var value: Int
        get() = 0
        set(value) { // Set setValue when value is called
            setValue = value
        }

    override fun initAction() {}

    override fun initView() {
        println("name: $name")
        println("name1: $name1")
        println("value: $value")
        value = 20
        println("setValue: $setValue")
    }

}