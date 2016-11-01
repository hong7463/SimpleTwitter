package com.codepath.apps.restclienttemplate.ui.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.ExtendedEntity;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.codepath.apps.restclienttemplate.models.Variant;
import com.codepath.apps.restclienttemplate.models.VideoInfo;
import com.codepath.apps.restclienttemplate.utils.AppUtils;
import com.malmstein.fenster.controller.MediaFensterPlayerController;
import com.malmstein.fenster.play.FensterPlayer;
import com.malmstein.fenster.view.FensterVideoView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    @BindView(R.id.details_profile_img)
    ImageView profileImg;
    @BindView(R.id.details_name)
    TextView name;
    @BindView(R.id.details_user_name)
    TextView userName;
    @BindView(R.id.details_text)
    TextView text;
    @BindView(R.id.details_img)
    ImageView image;
    @BindView(R.id.details_date)
    TextView date;
    @BindView(R.id.details_reply)
    ImageView reply;
    @BindView(R.id.details_video)
    FensterVideoView video;
    @BindView(R.id.details_controller)
    MediaFensterPlayerController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);
        postToUI();
    }

    private void postToUI() {
        Tweet tweet = (Tweet) Parcels.unwrap(getIntent().getExtras().getParcelable("tweet"));
        User user = tweet.getUser();
        Log.d(TAG, tweet.getExtendedEntity().getVideoInfo().getVariants().get(0).getUrl());
        if(tweet.getExtendedEntity() != null
                && tweet.getExtendedEntity().getVideoInfo() != null
                && tweet.getExtendedEntity().getVideoInfo().getVariants() != null
                && tweet.getExtendedEntity().getVideoInfo().getVariants().size() > 0
                && tweet.getExtendedEntity().getVideoInfo().getVariants().get(0) != null) {
            if(tweet.getExtendedEntity().getType().equals("video")) {
                video.setMediaController(controller);
                video.setVideo(tweet.getExtendedEntity().getVideoInfo().getVariants().get(0).getUrl(), MediaFensterPlayerController.DEFAULT_VIDEO_START);
                video.start();
            }
            else if(tweet.getExtendedEntity().getType().equals("photo")) {
                Glide.with(this).load(tweet.getEntity().getMedias().get(0).getMediaUrl()).centerCrop().into(image);
            }
        }
        Glide.with(this).load(user.getProfile_image_url()).fitCenter().into(profileImg);
        name.setText(user.getName());
        userName.setText("@" + user.getAtName());
        text.setText(tweet.getText());
        date.setText(AppUtils.getRelativeTimeAgo(tweet.getCreated()));
    }
}
