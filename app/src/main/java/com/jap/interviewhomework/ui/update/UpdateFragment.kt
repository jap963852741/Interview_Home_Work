package com.jap.interviewhomework.ui.update

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jap.interviewhomework.R
import com.jap.interviewhomework.databinding.FragmentUpdateBinding
import com.jap.interviewhomework.util.FragmentSwitchUtil
import com.jap.interviewhomework.util.FragmentSwitchUtil.Companion.TAB_HOME

class UpdateFragment : Fragment(){

    private lateinit var updateViewModel: UpdateViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val updateviewbinding = FragmentUpdateBinding.inflate(inflater, container, false);
        val toolbar: Toolbar = updateviewbinding.toolBarUpdate
        val updateBinding =  FragmentUpdateBinding.inflate(inflater, container, false)


        updateViewModel = ViewModelProvider(this,UpdateViewModelFactory(this,requireActivity().intent.extras))
                        .get(UpdateViewModel::class.java)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        toolbar.setNavigationIcon(R.drawable.ic_back_black)
        toolbar.setNavigationOnClickListener {
            FragmentSwitchUtil.getInstance(parentFragmentManager).selectedTab(TAB_HOME)
        }

        val condition =arrayOf<String?>("1","2","3","4")
        val conditionList: ArrayAdapter<String?> = ArrayAdapter(container!!.context,android.R.layout.simple_spinner_dropdown_item,condition)
        updateBinding.timezoneSpinner.setAdapter(conditionList)

        updateViewModel.intentDataStoreAsLiveData.observe(viewLifecycleOwner, Observer {
                updateBinding.userEmail.text = it.loginResponse.reportEmail
            }
        )

        updateViewModel.test_intent()
        return updateviewbinding.root
    }


}
