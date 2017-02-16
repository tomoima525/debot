package com.tomoima.debot;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tomoima.debot.adapter.DebotMenuListAdapter;
import com.tomoima.debot.strategy.DebotStrategy;

import java.util.ArrayList;


public class DebotDialog extends DialogFragment {
    private static final String TAG = "com.tomoima.debot.Debot";
    private ArrayList<DebotStrategy> debotStrategyList = new DebotStrategies().getStrategies();

    public static DebotDialog getInstance() {
        return new DebotDialog();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        onCancel(new DialogInterface() {
            @Override
            public void cancel() {
                dismissDebugMenu();
            }

            @Override
            public void dismiss() {
                dismissDebugMenu();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debot, container);
        ListView list = (ListView) view.findViewById(R.id.debug_menu_list);
        DebotMenuListAdapter adapter = new DebotMenuListAdapter(getActivity(), debotStrategyList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                debotStrategyList.get(position).startAction(getActivity());
                dismissDebugMenu();
            }
        });

        return view;
    }


    public void showDebugMenu(FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragmentManager != null) {
            if (fragmentManager.findFragmentByTag(TAG) == null) {
                show(fragmentManager, TAG);
                fragmentManager.executePendingTransactions();
            }
        }
    }

    public void dismissDebugMenu() {
        if (getActivity() == null) return;
        dismissDebugMenu(getActivity().getSupportFragmentManager());
    }

    @VisibleForTesting
    void dismissDebugMenu(FragmentManager fragmentManager) {

        DebotDialog debotDialog = (DebotDialog) fragmentManager.findFragmentByTag(TAG);
        if (debotDialog != null) {
            debotDialog.onDismiss(getDialog());
        }
    }
}
