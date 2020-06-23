package com.tomoima.debot.sample.fragments;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomoima.debot.sample.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class NextActivityFragment extends Fragment {

    public NextActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_next, container, false);
    }
}
