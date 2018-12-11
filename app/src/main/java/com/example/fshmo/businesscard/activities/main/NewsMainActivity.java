package com.example.fshmo.businesscard.activities.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.fshmo.businesscard.R;
import com.example.fshmo.businesscard.data.NewsItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.Unbinder;

public class NewsMainActivity extends AppCompatActivity implements MainFragmentListener {

    private static final String FEED_TAG = "News Feed Main Fragment";

    private TextView textView;
    private Unbinder unbinder;
    Toolbar toolbar;


    public static void start(Activity activity) {
        Intent intent = new Intent(activity, NewsMainActivity.class);
        activity.startActivity(intent);
    }

    public static boolean isTablet(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

        double wInches = displayMetrics.widthPixels / (double) displayMetrics.densityDpi;
        double hInches = displayMetrics.heightPixels / (double) displayMetrics.densityDpi;

        double screenDiagonal = Math.sqrt(Math.pow(wInches, 2) + Math.pow(hInches, 2));
        return (screenDiagonal >= 7.0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isTablet(this)) {
            toolbar = findViewById(R.id.main_feed_toolbar);
            setActionBar(toolbar);
            manageToolbar(isTablet(this));
        }
        setContentView(R.layout.activity_news_main);
        initializeFragments(isTablet(getApplicationContext()));
    }

    private void manageToolbar(boolean isTablet) {
        if (isTablet) {
            toolbar.setVisibility(View.VISIBLE);
        } else toolbar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onClicked(@NonNull NewsItem newsItem) {
        NewsDetailsFragment detailsFragment = NewsDetailsFragment.newInstance(newsItem.getId());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.news_additional_frame, detailsFragment)
                .commit();
        if (textView != null)
            textView.setVisibility(View.GONE);
    }

    @Override
    public void goToFeed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            getSupportFragmentManager().popBackStackImmediate();
    }

    private void initializeFragments(boolean isTablet) {
        initializeFeedFragment();
        if (isTablet) {
            textView = findViewById(R.id.additional_frame_empty_text);
            textView.setVisibility(View.VISIBLE);
        }
    }

    private void initializeFeedFragment() {
        NewsFeedFragment newsFeedFragment = new NewsFeedFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.news_main_frame, newsFeedFragment)
                .commit();
    }
}
