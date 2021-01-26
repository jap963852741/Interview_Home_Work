package com.jap.interviewhomework.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jap.interviewhomework.databinding.FragmentHomeBinding

class HomeFragment : Fragment(){

    private lateinit var homeviewbinding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    companion object {
        lateinit var homeViewModel: HomeViewModel
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        homeviewbinding = FragmentHomeBinding.inflate(inflater, container, false);
        val toolbar: Toolbar = homeviewbinding.toolBarHome

        homeViewModel = ViewModelProvider(this,HomeViewModelFactory()).get(HomeViewModel::class.java)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)


        homeViewModel.news()


        return homeviewbinding.root
    }


}
