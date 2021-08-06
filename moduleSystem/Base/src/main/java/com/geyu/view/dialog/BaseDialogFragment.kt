package com.geyu.view.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.geyu.callback.DialogListener

abstract class BaseDialogFragment<VDB:ViewDataBinding> : DialogFragment() {

    var mListener: DialogListener? = null
    var  mDataBind:VDB? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(getLayoutId(),container,false)
        mDataBind = DataBindingUtil.bind(rootView);
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

     open fun initView(){}

     open fun initData(){}

    fun setDialogListener(listener: DialogListener){
        mListener = listener;
    }

    abstract fun getLayoutId() : Int

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        mListener?.dismiss()
    }
}