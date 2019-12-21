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
import com.google.zxing.integration.android.IntentResult
import com.project.alcoholmembership.R
import kotlinx.android.synthetic.main.fragment_qrreader.view.*

class QRReaderFragment : Fragment() {

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
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(activity, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
