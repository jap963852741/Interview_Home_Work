package com.jap.interviewhomework.util

import android.R
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_update.*

open class SpinnerAdatper<String>(context: Context, resource: Int, list: ArrayList<String>) :
    ArrayAdapter<String>(context, resource, list) {

    var resource: Int
    var list: ArrayList<String>
    var vi: LayoutInflater

    init {
        this.resource = resource
        this.list = list
        this.vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var retView: View = super.getView(position, convertView, parent)
        retView.textAlignment = View.TEXT_ALIGNMENT_CENTER

        return retView
    }

    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val view: TextView = super.getDropDownView(
            position,
            convertView,
            parent
        ) as TextView
        // set item text bold and sans serif font
        view.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)

        // spinner item text color
        view.setTextColor(Color.parseColor("#2E2D88"))

        // spinner item text alignment center
        view.textAlignment = View.TEXT_ALIGNMENT_CENTER
        return view
    }

}