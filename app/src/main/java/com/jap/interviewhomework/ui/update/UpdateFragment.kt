package com.jap.interviewhomework.ui.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jap.interviewhomework.R
import com.jap.interviewhomework.databinding.FragmentUpdateBinding
import com.jap.interviewhomework.util.FragmentSwitchUtil
import com.jap.interviewhomework.util.FragmentSwitchUtil.Companion.TAB_HOME
import com.jap.interviewhomework.util.SpinnerAdatper
import org.angmarch.views.NiceSpinner
import org.angmarch.views.OnSpinnerItemSelectedListener

class UpdateFragment : Fragment(){

    private lateinit var updateViewModel: UpdateViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val updateviewbinding = FragmentUpdateBinding.inflate(inflater, container, false);
        val toolbar: Toolbar = updateviewbinding.toolBarUpdate
        val userEmail = updateviewbinding.userEmail

        updateViewModel = ViewModelProvider(this,UpdateViewModelFactory(this,requireActivity().intent.extras))
                        .get(UpdateViewModel::class.java)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        toolbar.setNavigationIcon(R.drawable.ic_back_black)
        toolbar.setNavigationOnClickListener {
            FragmentSwitchUtil.getInstance(parentFragmentManager).selectedTab(TAB_HOME)
        }
        updateviewbinding.changeTimezoneButton.visibility = INVISIBLE
        val timezonelist = arrayListOf<String?>("TimeZone","1","2","3","4")
        updateviewbinding.timezoneSpinner.setAdapter(SpinnerAdatper<String?>(container!!.context,
            android.R.layout.simple_spinner_dropdown_item,
            timezonelist))
        updateviewbinding.timezoneSpinner.setOnSpinnerItemSelectedListener(
            object  : OnSpinnerItemSelectedListener{
                override fun onItemSelected(
                    parent: NiceSpinner,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if(position == 0) {
                        updateviewbinding.changeTimezoneButton.visibility = INVISIBLE
                    }else {
                        updateviewbinding.changeTimezoneButton.visibility = VISIBLE
                    }
                }

            }
        )


        updateViewModel.logindata.observe(viewLifecycleOwner, Observer {
                updateviewbinding.userEmail.text = it.loginResponse.reportEmail
                updateviewbinding.userTimezone.text = it.loginResponse.timezone.toString()
            }
        )

        init()

        return updateviewbinding.root
    }

    fun init(){
        updateViewModel.Login_textview()
    }

}
