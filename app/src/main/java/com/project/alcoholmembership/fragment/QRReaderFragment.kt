package com.project.alcoholmembership.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import com.project.alcoholmembership.R
import com.project.alcoholmembership.model.User
import com.project.alcoholmembership.service.UserService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_qrreader.view.*
import javax.net.ssl.HttpsURLConnection


class QRReaderFragment : Fragment() {

    internal lateinit var mCompositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater!!.inflate(R.layout.fragment_qrreader, container, false)

        val integrator = FragmentIntentIntegrator(this)

        view.apply {
            reader.setOnClickListener{
                integrator.initiateScan()
                integrator.setBeepEnabled(false)//바코드 인식시 소리
            }
        }
        return view
    }

    // QR Code Get the results
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        val qrCode:String = result.contents
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(activity, "Fail", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Success: " + result.contents, Toast.LENGTH_LONG).show()
//                val user = Gson().fromJson(result.contents, User::class.java)
                //network code
                mCompositeDisposable = CompositeDisposable()

                mCompositeDisposable.add(
                    UserService.instance.getUserInfo(qrCode)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext() { res ->
                            if (res.code() == HttpsURLConnection.HTTP_OK) {
                                setUserInfo(res.body()!!)
                            }
                        }
                        .subscribe())
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun setUserInfo(user:User){
        val args = Bundle()
//                args.putString("key", "value")
        val choiceDialog = ChoiceDialog()
        choiceDialog.newInstance(user)
//                choiceDialog.arguments = args; // 데이터 전달
        choiceDialog.show(activity?.supportFragmentManager,"tag")
    }

}
