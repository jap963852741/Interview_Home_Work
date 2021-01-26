package com.jap.interviewhomework.ui.home

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
import com.jap.interviewhomework.databinding.FragmentHomeBinding
import com.jap.interviewhomework.util.FragmentSwitchUtil
import com.jap.interviewhomework.util.FragmentSwitchUtil.Companion.TAB_UPDATE
import com.jap.interviewhomework.util.SpacesItemDecoration

class HomeFragment : Fragment(){

    private lateinit var homeviewbinding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    companion object {
        lateinit var homeViewModel: HomeViewModel
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        homeviewbinding = FragmentHomeBinding.inflate(inflater, container, false);
        val toolbar: Toolbar = homeviewbinding.toolBarHome
        val recyclerView: RecyclerView = homeviewbinding.reView

        homeViewModel = ViewModelProvider(this,HomeViewModelFactory()).get(HomeViewModel::class.java)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        homeViewModel.News.observe(viewLifecycleOwner, Observer {
            val newsResult = it ?: return@Observer

            if (newsResult.error != null) {
                showNewsFailed(newsResult.error)
            }
            if (newsResult.success != null) {
                homeAdapter = HomeAdapter(it.success, container!!)
                recyclerView.setAdapter(homeAdapter)
                recyclerView.addItemDecoration(
                    DividerItemDecoration(context,
                        DividerItemDecoration.VERTICAL)
                )
                recyclerView.addItemDecoration(SpacesItemDecoration(100))
                recyclerView.setLayoutManager(
                    LinearLayoutManager(
                        context,
                        RecyclerView.VERTICAL,
                        false
                    )
                )
            }
        })

        homeviewbinding.toolbarButton.setOnClickListener{
            val fragmentManager = parentFragmentManager
            val fragmentutil = FragmentSwitchUtil.getInstance(fragmentManager)
            fragmentutil.selectedTab(TAB_UPDATE)
        }

        init()

        return homeviewbinding.root
    }

    private fun showNewsFailed(@StringRes errorString: Int) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show()
    }
    private fun init(){
        homeViewModel.news()
    }
}
