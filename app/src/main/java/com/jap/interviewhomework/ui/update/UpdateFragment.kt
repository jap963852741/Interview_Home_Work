package com.jap.interviewhomework.ui.update

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jap.interviewhomework.R
import com.jap.interviewhomework.databinding.FragmentUpdateBinding
import com.jap.interviewhomework.ui.login.LoginViewModel
import com.jap.interviewhomework.util.FragmentSwitchUtil
import com.jap.interviewhomework.util.FragmentSwitchUtil.Companion.TAB_HOME

class UpdateFragment : Fragment(){

    private lateinit var homeViewModel: UpdateViewModel

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val updateviewbinding = FragmentUpdateBinding.inflate(inflater, container, false);
        val toolbar: Toolbar = updateviewbinding.toolBarUpdate

        homeViewModel = ViewModelProvider(this,UpdateViewModelFactory()).get(UpdateViewModel::class.java)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        toolbar.setNavigationIcon(R.drawable.ic_back_black)
        toolbar.setNavigationOnClickListener {
            FragmentSwitchUtil.getInstance(parentFragmentManager).selectedTab(TAB_HOME)
        }

        return updateviewbinding.root
    }


}
