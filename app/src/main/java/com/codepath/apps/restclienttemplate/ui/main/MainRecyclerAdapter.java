package com.codepath.apps.restclienttemplate.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Entity;
import com.codepath.apps.restclienttemplate.models.Media;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.ui.detail.DetailsActivity;
import com.codepath.apps.restclienttemplate.utils.AppUtils;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by hison7463 on 10/30/16.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {

    private final static String TAG = MainRecyclerAdapter.class.getSimpleName();
    private Context context;
    private List<Tweet> list;

    public MainRecyclerAdapter(Context context, List<Tweet> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        MainViewHolder viewHolder = new MainViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        final Tweet tweet = list.get(position);
        User user = tweet.getUser();
        Entity entity = tweet.getEntity();
        Media media = null;
        if(entity != null && entity.getMedias() != null && entity.getMedias().size() > 0) {
            media = entity.getMedias().get(0);
        }
        Glide.with(context).load(user.getProfile_image_url()).into(holder.profileImg);
        holder.name.setText(user.getName());
        holder.atName.setText("@" + user.getAtName());
        holder.text.setText(Html.fromHtml(tweet.getText()));
        holder.date.setText(AppUtils.getRelativeTimeAgo(tweet.getCreated()));
        if(media != null && media.getMediaUrl() != null) {
            holder.thumb.setVisibility(View.VISIBLE);
            Glide.with(context).load(media.getMediaUrl()).centerCrop().into(holder.thumb);
        }
        else {
            holder.thumb.setVisibility(View.GONE);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click");
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("tweet", Parcels.wrap(tweet));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.list_item_view)
        View view;
        @BindView(R.id.list_item_img)
        ImageView profileImg;
        @BindView(R.id.list_item_name)
        TextView name;
        @BindView(R.id.list_item_text)
        TextView text;
        @BindView(R.id.list_item_date)
        TextView date;
        @BindView(R.id.list_item_at_name)
        TextView atName;
        @BindView(R.id.list_item_thumb)
        ImageView thumb;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
