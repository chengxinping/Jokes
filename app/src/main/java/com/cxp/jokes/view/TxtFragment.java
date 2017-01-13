package com.cxp.jokes.view;

import android.util.Log;
import android.view.View;

import com.cxp.jokes.R;
import com.cxp.jokes.model.TxtJokesModel;
import com.cxp.jokes.presenter.TxtJokesPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cxp on 17-1-13.
 */

public class TxtFragment extends BaseFragment implements BaseView {
    private int page = 1;
    Date currentTime = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    String timestamp = formatter.format(currentTime);

    @Override
    public void initData() {

    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        initPresenter();
        return view;
    }

    private void initPresenter() {
        TxtJokesPresenter presenter = new TxtJokesPresenter(this);
        presenter.getData(timestamp, page);
    }

    @Override
    public void success(Object object) {
    }

    @Override
    public void fail(String s) {

    }
}
