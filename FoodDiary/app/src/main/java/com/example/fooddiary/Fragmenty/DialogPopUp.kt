package com.example.fooddiary.Fragmenty

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import com.example.fooddiary.MainActivity
import com.example.fooddiary.databinding.DialogLayoutBinding
import java.io.InputStream

class DialogPopUp(val name:String,val protein:String,val kcal:String,val uri:Uri,val x:String) : DialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val b = DialogLayoutBinding.inflate(inflater,container,false)
        val rootView: View = b.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        b.namaewa.setText(name)
        b.pam.setText(protein)
        b.kcalam.setText(kcal)

        if (x=="1"){
            val inputStream: InputStream? = context?.getContentResolver()?.openInputStream(uri)
            val dr = Drawable.createFromStream(inputStream, uri.toString())
            b.img.background = dr
        }else
            b.img.setImageURI(uri)

        b.eatbtn.setOnClickListener {
            if(!(b.amountnumber.text.toString().isNullOrEmpty())){
                dismiss()
                MainActivity.proteineaten += (b.amountnumber.text.toString()).toDouble() * (protein.toDouble()/100)
                MainActivity.kcaleaten += (b.amountnumber.text.toString()).toDouble() * (kcal.toDouble()/100)
                Toast.makeText(context,"You have eaten ${b.amountnumber.text} grams of ${name}",Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(context,"How much ?",Toast.LENGTH_SHORT).show()
        }
        b.cookbtn.setOnClickListener {
            if(!(b.amountnumber.text.toString().isNullOrEmpty())){
                dismiss()

            }else
                Toast.makeText(context,"How much ?",Toast.LENGTH_SHORT).show()


        }

        return rootView
    }
}