package com.cxp.jokes.view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cxp.jokes.R;

public class PicJokesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_pic_jokes);
        String title = getIntent().getStringExtra("title");
        String img = getIntent().getStringExtra("img");
        String time = getIntent().getStringExtra("time");
        TextView mTitle = (TextView) findViewById(R.id.activity_pic_text);
        TextView mTime = (TextView) findViewById(R.id.activity_pic_time);
        ImageView mImg = (ImageView) findViewById(R.id.activity_pic_jokes);
        Glide.with(getApplicationContext())
                .load(img)
                .centerCrop()
                .dontAnimate()
                .priority(Priority.priority.HIGH)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading_12)
                .into(mImg);
        mTitle.setText(title);
        mTime.setText(time);
    }
}
