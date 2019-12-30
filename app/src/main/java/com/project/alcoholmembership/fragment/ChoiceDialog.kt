package com.project.alcoholmembership.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.gson.Gson
import com.project.alcoholmembership.R
import com.project.alcoholmembership.model.User
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.dialog_choice.view.*
import kotlinx.android.synthetic.main.fragment_qrcode.view.*


class ChoiceDialog : DialogFragment() {
    internal lateinit var mCompositeDisposable: CompositeDisposable
    private val TAG: String = "ChoiceDialog"
    val ARG_DIALOG_MAIN_MSG = "dialog_main_msg"
    lateinit var user : User

    fun newInstance(user: User): ChoiceDialog {
        this.user = user
        Log.d("@@@user qrcode3",""+user.qrid )
        val bundle = Bundle()
        val fragment = ChoiceDialog()
        fragment.arguments = bundle
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (arguments != null) {
//            mMainMsg = arguments!!.getString(ARG_DIALOG_MAIN_MSG)
//        }
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.dialog_choice, container, false)
        val dialog = dialog
        dialog.setCanceledOnTouchOutside(false)

        view.apply {
            qr.text = user.qrid
        }

        return view
    }

}