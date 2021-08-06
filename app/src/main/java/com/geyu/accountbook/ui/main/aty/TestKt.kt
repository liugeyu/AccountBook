package com.geyu.accountbook.ui.main.aty

import com.geyu.utils.LLOG

class TestKt  {
    fun test(){
        var animal = "cat"
        var ret = animal.run {
            this.toUpperCase()
            LLOG.e("in $this");

            animal
        }
        LLOG.e("run $ret");
        LLOG.e("out" +animal)       //cat


        "".let {
            it.length
        }

        "".also {
            it.length
        }.toUpperCase()
        "".apply {

        }

        animal.run(){

        }
        with("null"){
            LLOG.d(this);
            "test"
        }
        test2(null)
    }

    private fun test2(str: String?) {
        str.apply {
            LLOG.e("test2$this")
        }?.apply {
            LLOG.e("test2"+this)
        }
        var str:String? = null;
        str.apply {
            LLOG.e("空也可以 apply?")
        }
        LLOG.e("${str?.length}")
        var list = ArrayList<String>();
        list.add("")

        var ints = arrayOf(1)
        ints.forEach lit@ {
            if (it == 0) return@lit
            print(it)
        }

    }

    public fun test3(callbakc:TestKt.() ->Int){
        var v = callbakc()
    }
    public class InTest{
        fun init(){}
    }


    interface Callback{
        fun callback()
    }
}