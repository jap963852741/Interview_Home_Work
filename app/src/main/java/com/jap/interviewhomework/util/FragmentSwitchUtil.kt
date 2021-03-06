package com.jap.interviewhomework.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.jap.interviewhomework.R
import com.jap.interviewhomework.ui.home.HomeFragment
import com.jap.interviewhomework.ui.update.UpdateFragment
import java.util.*

class FragmentSwitchUtil constructor(fragmanager  : FragmentManager){

    var mStacks: HashMap<String, Stack<Fragment>>? = null
    var mCurrentTab: String? = null
    var manager: FragmentManager

    init{
        manager = fragmanager
        mStacks = HashMap<String, Stack<Fragment>>()
        mStacks!!.put(TAB_HOME, Stack<Fragment>())
        mStacks!!.put(TAB_UPDATE, Stack<Fragment>())
    }

    companion object {
        val TAB_HOME = "tab_home"
        val TAB_UPDATE = "tab_update"
        private var INSTANCE: FragmentSwitchUtil? = null
    }

    //singleton
    fun getInstance(): FragmentSwitchUtil {
        if (INSTANCE == null) {
            INSTANCE = FragmentSwitchUtil(manager)
        }
        return INSTANCE!!
    }

    fun init(homeFragment: HomeFragment){
        if(mStacks!![TAB_HOME]!!.size == 0) {
            mStacks!![TAB_HOME]!!.push(homeFragment)
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.add(R.id.nav_host_fragment, homeFragment).commit()
        }else{
            selectedTab(TAB_HOME)
        }
    }

    /**
      *    First time this tab is selected. So add first fragment of that tab.
      *    Dont need animation, so that argument is false.
      *    We are adding a new fragment which is not present in stack. So add to stack is true.
      */
    fun selectedTab(tabId: String) {
        mCurrentTab = tabId
        if (mStacks!![tabId]!!.size == 0) {
            if (tabId == TAB_UPDATE) {
                switchContent(getNowFragment(), UpdateFragment() , tabId ,true)
            }
        } else {
            switchContent(getNowFragment(),mStacks!![tabId]!!.lastElement() , tabId ,false)
        }
    }

    /**
     * 切换fragment
     * @param from the fragment want to hide
     * @param to the fragment want to show
     */
    fun switchContent(
        from: Fragment?,
        to: Fragment,
        tag :String?,
        init : Boolean
    ) {
        if (init) mStacks!![tag]!!.push(to)
        if (from !== to) {
            val transaction: FragmentTransaction = manager.beginTransaction()
            if (!to.isAdded) {
                transaction.hide(from!!)
                transaction.add(R.id.nav_host_fragment, to)
                transaction.show(to).commit()
            } else {
                transaction.hide(from!!)
                transaction.show(to).commit()
            }
        }
    }

    fun getNowFragment() : Fragment?{
        val fragments: List<Fragment> = manager.getFragments()
        val i = fragments.size-1
        for (i in 0..fragments.size-1) {
            val j = fragments.size - 1 - i
            if (fragments[j].isVisible) {
                return fragments[j]
            }
        }
        return null
    }

}