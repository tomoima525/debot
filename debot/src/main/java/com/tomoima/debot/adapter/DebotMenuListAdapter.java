package com.tomoima.debot.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tomoima.debot.R;
import com.tomoima.debot.strategy.DebotStrategy;

import java.util.List;


public class DebotMenuListAdapter extends ArrayAdapter<DebotStrategy> {
    private LayoutInflater inflater;

    public DebotMenuListAdapter(Context context, List<DebotStrategy> objects) {
        super(context, -1, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh;
        if (view == null) {
            view = inflater.inflate(R.layout.row_debug_menu, parent, false);
            vh = new ViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.menuTitle.setText(getItem(position).getStrategyMenuName());
        return view;
    }


    private static class ViewHolder {
        TextView menuTitle;

        ViewHolder(View view) {
            menuTitle = (TextView) view.findViewById(R.id.debug_menu_title);
        }
    }
}
