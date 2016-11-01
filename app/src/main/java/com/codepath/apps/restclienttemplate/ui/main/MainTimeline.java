package com.codepath.apps.restclienttemplate.ui.main;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.utils.AppUtils;
import com.codepath.apps.restclienttemplate.utils.TwitterClient;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

public class MainTimeline extends AppCompatActivity {

    private static final String TAG = MainTimeline.class.getSimpleName();
    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_fab)
    FloatingActionButton fab;
    @BindView(R.id.activity_main_timeline)
    SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    private List<Tweet> tweets = new ArrayList<>();
    private MainRecyclerAdapter adapter;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_timeline);

        ButterKnife.bind(this);
        callTimeline();
        initRecyclerView();
        initClickListener();
    }

    @Override
    protected void onResume() {
        super.onPause();
    }

    private void initClickListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComposeDialog dialog = new ComposeDialog();
                dialog.setSinceId(tweets.get(0).getId());
                dialog.show(getSupportFragmentManager(), "compose");
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callAfterPost(tweets.get(0).getId());
            }
        });
    }

    private void initRecyclerView() {
        adapter = new MainRecyclerAdapter(MainTimeline.this, tweets);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
//                AppUtils.getClient().getHomeTimeline(page, new JsonHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                        tweets.addAll(Tweet.fromJsonArray(response));
//                        adapter.notifyDataSetChanged();
//                    }
//                });
                callTimeline(tweets.get(tweets.size() - 1).getId());
            }
        });
    }

    public void callAfterPost(long since_id) {
        RequestParams params = new RequestParams();
        params.put("since_id", since_id);
        AppUtils.getClient().getHomeTimeline(params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                tweets.addAll(0, Tweet.fromJsonArray(array));
                adapter.notifyItemRangeInserted(0, array.length());
                recyclerView.scrollToPosition(0);
                if(swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    private void callTimeline(long maxId) {
        RequestParams params = new RequestParams();
        params.put("max_id", maxId);
        AppUtils.getClient().getHomeTimeline(params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray array) {
                int start = tweets.size();
                tweets.addAll(Tweet.fromJsonArray(array));
                adapter.notifyItemRangeInserted(start, array.length());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject object) {
                if(object != null) {
                    Log.d(TAG, object.toString());
                }
                else {
                    Log.d(TAG, "fail");
                }
            }
        });
    }

    public void callTimeline() {
        AppUtils.getClient().getHomeTimeline(0, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d(TAG, json.toString());
                tweets.addAll(Tweet.fromJsonArray(json));
                adapter.notifyDataSetChanged();
                Log.d(TAG, layoutManager.getItemCount() + "");
                Log.d(TAG, adapter.getItemCount() + "");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d(TAG, errorResponse.toString());
            }
        });
    }

}
