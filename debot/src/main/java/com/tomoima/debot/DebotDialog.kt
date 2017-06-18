package com.tomoima.debot

import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.tomoima.debot.adapter.DebotMenuListAdapter

// This class allows others to inherit
open class DebotDialog : DialogFragment() {
    private val TAG = "com.tomoima.debot.Debot"
    private val debotStrategyList = DebotStrategies.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
        onCancel(object : DialogInterface {
            override fun cancel() {
                dismissDebugMenu()
            }

            override fun dismiss() {
                dismissDebugMenu()
            }
        })
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_debot, container)
        val list = view.findViewById(R.id.debug_menu_list) as ListView
        val adapter = DebotMenuListAdapter(activity, debotStrategyList)
        list.adapter = adapter
        list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            debotStrategyList[position].startAction(activity)
            dismissDebugMenu()
        }

        return view
    }


    fun showDebugMenu(activity: FragmentActivity?) {
        val fragmentManager = activity?.supportFragmentManager as? FragmentManager
        if (fragmentManager != null && fragmentManager.findFragmentByTag(TAG) == null) {
                show(fragmentManager, TAG)
                fragmentManager.executePendingTransactions()
        }
    }

    fun dismissDebugMenu() {
        activity?.let { dismissDebugMenu(it.supportFragmentManager) }
    }

    @VisibleForTesting
    fun dismissDebugMenu(fragmentManager: FragmentManager) {

        val debotDialog: DebotDialog? = fragmentManager.findFragmentByTag(TAG) as DebotDialog
        debotDialog?.onDismiss(dialog)
    }

    companion object {
        fun get(): DebotDialog = DebotDialog()
    }
}
