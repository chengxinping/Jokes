package com.cxp.jokes.view;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cxp.jokes.R;
import com.cxp.jokes.model.TxtJokesModel;
import com.cxp.jokes.presenter.TxtJokesPresenter;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cxp on 17-1-13.
 */

public class TxtFragment extends BaseFragment implements BaseView {
    private int page = 1;
    private String timestamp;
    private RecyclerView mRecyclerView;
    private List<TxtJokesModel.ShowapiResBodyBean.ContentlistBean> mList;

    @Override
    public void initData() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        timestamp = formatter.format(currentTime);
        initPresenter();
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.content_recycler_view);
        Log.d("TAG", "success: " + mList);
        return view;
    }

    private void initPresenter() {
        TxtJokesPresenter presenter = new TxtJokesPresenter(this);
        presenter.getData(timestamp, page);
    }

    @Override
    public void success(Object object) {
        mList = ((TxtJokesModel) object).getShowapi_res_body().getContentlist();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(new CommonAdapter<TxtJokesModel.ShowapiResBodyBean.ContentlistBean>(mActivity, R.layout.item_txt_jokes, mList) {

            @Override
            protected void convert(ViewHolder holder, TxtJokesModel.ShowapiResBodyBean.ContentlistBean contentlistBean, int position) {
                holder.setText(R.id.jokes_txt_text, contentlistBean.getText());
                holder.setText(R.id.jokes_txt_time, contentlistBean.getCt());
                holder.setText(R.id.jokes_txt_title, contentlistBean.getTitle());
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivity, TxtJokesActivity.class);
                        intent.putExtra("title", mList.get(position).getTitle());
                        intent.putExtra("text", mList.get(position).getText());
                        intent.putExtra("time", mList.get(position).getCt());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public void fail(String s) {
        Toast.makeText(mActivity, "数据加载失败！", Toast.LENGTH_SHORT).show();
    }
}
