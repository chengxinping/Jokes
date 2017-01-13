package com.cxp.jokes.view;

import android.view.View;

import com.cxp.jokes.R;

/**
 * Created by cxp on 17-1-13.
 */

public class GifFragment extends BaseFragment {
    @Override
    public void initData() {

    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        return view;
    }
}
