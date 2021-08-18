package com.geyu.view.dialog

import com.geyu.base.R
import com.geyu.base.databinding.DialogLoadingBinding

class LoadingDialog: BaseDialogFragment<DialogLoadingBinding>() {




    override fun getLayoutId(): Int {
        return R.layout.dialog_loading
    }


}