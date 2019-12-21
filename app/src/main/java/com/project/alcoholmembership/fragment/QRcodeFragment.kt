package com.project.alcoholmembership.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.project.alcoholmembership.R
import kotlinx.android.synthetic.main.fragment_qrcode.view.*

class QRcodeFragment : Fragment() {

    private val text = null
    private val ivQrcode = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater!!.inflate(R.layout.fragment_qrcode, container, false)

        val text = "test"

        val multiFormatWriter : MultiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            view.qrcode.setImageBitmap(bitmap)
        } catch (e: Exception) {
        }



        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
