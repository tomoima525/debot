package com.tomoima.debot.sample.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import androidx.fragment.app.Fragment;
import android.view.View;

import com.tomoima.debot.sample.MyApplication;


public class BaseFragment extends Fragment {

    public Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findView(View view, int resId) {
        return (T) view.findViewById(resId);
    }

    protected void finish() {
        if (isResumed())
            getActivity().finish();
    }

    /**
     * {@link Fragment#getResources()} sometimes fails because a fragment is not attached to an activity<br>
     * Use this method to call getResources from Application<br>
     *
     * @return Resources
     */
    public Resources getResourcesFromApplication() {
        return MyApplication.getInstance().getResources();
    }

}
