package com.qti.splitbill.ui

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.qti.splitbill.R
import com.qti.splitbill.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var activeBillsFragment: ActiveBillsFragment? = null
    private var historyBillsFragment: HistoryBillsFragment? = null
    private var settingFragment: SettingFragment? = null
    private var navigation: BottomNavigationView? = null
    var doubleBackToExitPressedOnce = false

    private val BACK_STACK_ROOT_TAG = "root_fragment"

    val fm = supportFragmentManager

    private var mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.split_activie -> {
                    loadFragment(activeBillsFragment!!)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.split_history -> {
                    loadFragment(historyBillsFragment!!)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.setting -> {
                    loadFragment(settingFragment!!)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()

    }

    private fun initView() {
        navigation = binding.navMenu
        activeBillsFragment = ActiveBillsFragment()
        historyBillsFragment = HistoryBillsFragment()
        settingFragment = SettingFragment()
        //active = homeFragment;
        navigation!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation!!.selectedItemId = R.id.split_activie
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.frameContainer, fragment)
        transaction.addToBackStack(BACK_STACK_ROOT_TAG)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (navigation!!.selectedItemId != R.id.split_activie) {
            loadFragment(activeBillsFragment!!)
            navigation!!.menu.findItem(R.id.split_activie).isChecked = true
        } else {
            if (doubleBackToExitPressedOnce) {
                fm.popBackStack(
                    BACK_STACK_ROOT_TAG,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                super.onBackPressed()
                return
            }
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }
}