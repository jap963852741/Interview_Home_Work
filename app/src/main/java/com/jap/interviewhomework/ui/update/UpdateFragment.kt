package com.jap.interviewhomework.ui.update

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jap.interviewhomework.R
import com.jap.interviewhomework.databinding.FragmentUpdateBinding
import com.jap.interviewhomework.ui.login.LoginActivity.Companion.LOGIN_DATA
import com.jap.interviewhomework.ui.login.LoginDataResult
import com.jap.interviewhomework.ui.main.MainActivity
import com.jap.interviewhomework.util.FragmentSwitchUtil
import com.jap.interviewhomework.util.FragmentSwitchUtil.Companion.TAB_HOME
import com.jap.interviewhomework.util.SpinnerAdatper
import kotlinx.android.synthetic.main.item_home.*
import kotlin.properties.Delegates

//import org.angmarch.views.NiceSpinner
//import org.angmarch.views.OnSpinnerItemSelectedListener

class UpdateFragment : Fragment(){

    private lateinit var updateViewModel: UpdateViewModel
    private lateinit var sessionToken: String
    private lateinit var objectId: String
    private var SelectedTimeZone by Delegates.notNull<Int>()

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val updateviewbinding = FragmentUpdateBinding.inflate(inflater, container, false);
        val toolbar: Toolbar = updateviewbinding.toolBarUpdate

        updateViewModel = ViewModelProvider(this,UpdateViewModelFactory(this,requireActivity().intent.extras))
                        .get(UpdateViewModel::class.java)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        toolbar.setNavigationIcon(R.drawable.ic_back_black)
        toolbar.setNavigationOnClickListener {
            FragmentSwitchUtil(parentFragmentManager).getInstance().selectedTab(TAB_HOME)
        }
        val timezonelist = arrayListOf<String?>("TimeZone","1","2","3","4")
        updateviewbinding.timezoneSpinner.setAdapter(SpinnerAdatper<String?>(container!!.context,
            android.R.layout.simple_spinner_dropdown_item,
            timezonelist))

        updateviewbinding.timezoneSpinner.setOnItemSelectedListener(
            object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    updateviewbinding.changeTimezoneButton.isEnabled = ( position != 0 )
                    if(position != 0)
                        SelectedTimeZone = updateviewbinding.timezoneSpinner.selectedItem.toString().toInt()
                }

            }

        )

        updateViewModel.logindata.observe(viewLifecycleOwner, Observer {
                updateviewbinding.userEmail.text = it.loginResponse.reportEmail
                sessionToken = it.loginResponse.sessionToken
                objectId = it.loginResponse.objectId
            }
        )

        updateViewModel.updateResult.observe(viewLifecycleOwner, Observer {
            val updateResult = it ?: return@Observer

            updateviewbinding.loadingUpdate.visibility = GONE
            if (updateResult.error != null) {
                UpdateFailed(updateResult.error)
            }
            if (updateResult.success != null) {
                updateSuccess(updateResult.success)
            }
        })


        updateviewbinding.changeTimezoneButton.setOnClickListener {
            updateviewbinding.loadingUpdate.visibility = VISIBLE
            updateViewModel.updata(sessionToken , objectId , Timezone(timezone = SelectedTimeZone))
        }

        init()

        return updateviewbinding.root
    }

    fun init(){
        updateViewModel.LoginData_textview()
    }

    private fun updateSuccess(model: UpdateDataResult) {
        val login_success = getString(R.string.update_success)
        Toast.makeText(
            context,
            "$login_success ${model.updateResponse}",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun UpdateFailed(@StringRes errorString: Int) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show()
    }
}
