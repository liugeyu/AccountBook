package com.geyu.view.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.geyu.base.R
import com.geyu.callback.DialogListener
import com.geyu.utils.LLOG
import com.gyf.immersionbar.ImmersionBar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseDialogFragment<VDB : ViewDataBinding> : DialogFragment() {
    private var compositeDisposable: CompositeDisposable? = null
    private val DEFAULT_DIM = 0.2f
    /**
     * @Describe dialogFragment 从上往下出现
     */
    protected val TYPE_TOP = 1

    /**
     * @Describe dialogFragment 从下往上出现
     */
    protected val TYPE_BOTTOM = 2

    /**
     * @Describe dialogFragment 从左往右出现
     */
    protected val TYPE_LEFT = 3

    /**
     * @Describe dialogFragment 从右往左出现
     */
    protected val TYPE_RIGHT = 4

    var mListener: DialogListener? = null
    lateinit var  mDataBind:VDB

    private val FragmentTag = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, getStyle())
    }
    open fun setDisplayAnimation(): Int {
        return TYPE_BOTTOM
    }
    override fun onStart() {
        super.onStart()
        var window = dialog?.window
        var layoutParams = window?.attributes
        layoutParams?.run {
            dimAmount =  getDimAmount()
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = if (getHeight() > 0) {
                getHeight()
            } else {
                if (isHeightMatchParent()) {
                    WindowManager.LayoutParams.MATCH_PARENT
                } else {
                    WindowManager.LayoutParams.WRAP_CONTENT
                }
            }
            gravity = getGravity()
        }
        window?.attributes = layoutParams
    }

    open fun getGravity(): Int {
        return Gravity.BOTTOM
    }

    open fun isHeightMatchParent(): Boolean {
        return false
    }

    open fun getHeight():Int{
        return -1;
    }
     open fun getDimAmount(): Float {
         return DEFAULT_DIM
     }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        val rootView = inflater.inflate(getLayoutId(), container, false)
        mDataBind = DataBindingUtil.bind(rootView)!!
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ImmersionBar.with(this).navigationBarColor(getStateBarColor()).keyboardEnable(true).init()
        initView()
        initData()
    }

    open fun getStateBarColor(): Int{
        return R.color.white
    }

    @StyleRes
    open fun getStyle(): Int {
        return when (setDisplayAnimation()) {
            TYPE_TOP -> R.style.BaseDialogUpFragmentStyle
            TYPE_BOTTOM -> R.style.BaseDialogBottomFragmentStyle
            TYPE_LEFT -> R.style.BaseDialogLeftFragmentStyle
            TYPE_RIGHT -> R.style.BaseDialogRightFragmentStyle
            else -> R.style.BaseDialogBottomFragmentStyle
        }
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


    fun show(fragmentManager: FragmentManager) {
        LLOG.e("tag: $FragmentTag")
        show(fragmentManager, FragmentTag)
    }

    fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(disposable)
    }
    override fun onDestroy() {
        super.onDestroy()
       compositeDisposable?.run {
           if (!isDisposed) {
               dispose()
           }
       }
    }
}