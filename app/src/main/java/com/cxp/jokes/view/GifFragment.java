package com.cxp.jokes.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cxp.jokes.R;
import com.cxp.jokes.model.GifJokesModel;
import com.cxp.jokes.presenter.GifJokesPresenter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cxp on 17-1-13.
 */

public class GifFragment extends BaseFragment implements BaseView {
    private int maxResult;
    private String timestamp;
    private XRecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private List<GifJokesModel.ShowapiResBodyBean.ContentlistBean> mList;

    @Override
    public void initData() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        timestamp = formatter.format(currentTime);
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initPresenter();
                mRecyclerView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);
            }
        }, 800);
    }


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.content_recycler_view);
        return view;
    }

    private void initPresenter() {
        maxResult = 10;
        GifJokesPresenter presenter = new GifJokesPresenter(this);
        presenter.getData(timestamp, maxResult);
    }

    @Override
    public void success(Object object) {
        mList = ((GifJokesModel) object).getShowapi_res_body().getContentlist();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(new CommonAdapter<GifJokesModel.ShowapiResBodyBean.ContentlistBean>(mActivity, R.layout.item_pic_jokes, mList) {
                                     @Override
                                     protected void convert(ViewHolder holder, GifJokesModel.ShowapiResBodyBean.ContentlistBean contentlistBean, int position) {
                                         Glide.with(mActivity)
                                                 .load(contentlistBean.getImg())
                                                 .asGif()
                                                 .centerCrop()
                                                 .dontAnimate()
                                                 .priority(Priority.priority.HIGH)
                                                 .skipMemoryCache(true)
                                                 .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                                 .placeholder(R.drawable.loading_12)
                                                 .into((ImageView) holder.getView(R.id.jokes_pic_img));
                                         holder.setText(R.id.jokes_pic_title, contentlistBean.getTitle());
                                     }

                                     @Override
                                     public void onBindViewHolder(ViewHolder holder, final int position) {
                                         super.onBindViewHolder(holder, position);
                                         holder.itemView.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 Intent intent = new Intent(mActivity, DetailJokesActivity.class);
                                                 intent.putExtra("img", mList.get(position).getImg());
                                                 startActivity(intent);
                                             }
                                         });
                                     }
                                 }
        );
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initPresenter();
                        mRecyclerView.refreshComplete();
                    }
                }, 800);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GifJokesPresenter presenter = new GifJokesPresenter(GifFragment.this);
                        maxResult = maxResult + 10;
                        presenter.getData(timestamp, maxResult);
                        mRecyclerView.loadMoreComplete();
                    }
                }, 800);
            }
        });
        mRecyclerView.scrollToPosition(maxResult - 10);
    }


    @Override
    public void fail(String s) {
        Toast.makeText(mActivity, "数据加载失败！", Toast.LENGTH_SHORT).show();
    }
}
