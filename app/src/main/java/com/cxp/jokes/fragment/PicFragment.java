package com.cxp.jokes.fragment;

import android.view.View;

import com.cxp.jokes.R;
import com.cxp.jokes.base.BaseFragment;

/**
 * Created by cxp on 17-1-13.
 */

public class PicFragment extends BaseFragment {
    @Override
    public void initData() {

    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        return view;
    }
}
