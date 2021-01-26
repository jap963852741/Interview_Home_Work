package com.jap.interviewhomework.ui.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jap.interviewhomework.R
import com.jap.interviewhomework.databinding.FragmentHomeBinding
import com.jap.interviewhomework.databinding.FragmentUpdateBinding
import com.jap.interviewhomework.util.FragmentSwitchUtil
import com.jap.interviewhomework.util.FragmentSwitchUtil.Companion.TAB_HOME
import com.jap.interviewhomework.util.SpacesItemDecoration

class UpdateFragment : Fragment(){

    private lateinit var updateviewbinding: FragmentUpdateBinding

    companion object {
        lateinit var homeViewModel: UpdateViewModel
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        updateviewbinding = FragmentUpdateBinding.inflate(inflater, container, false);
        val toolbar: Toolbar = updateviewbinding.toolBarUpdate

        homeViewModel = ViewModelProvider(this,UpdateViewModelFactory()).get(UpdateViewModel::class.java)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        toolbar.setNavigationIcon(R.drawable.ic_back_black)
        toolbar.setNavigationOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentutil = FragmentSwitchUtil.getInstance(fragmentManager)
            fragmentutil.selectedTab(TAB_HOME)
        }

        return updateviewbinding.root
    }


}
