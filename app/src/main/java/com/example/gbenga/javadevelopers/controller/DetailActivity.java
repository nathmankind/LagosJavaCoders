package com.example.gbenga.javadevelopers.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gbenga.javadevelopers.R;

/**
 * Created by GBENGA on 9/15/2017.
 */

public class DetailActivity extends AppCompatActivity {
    TextView Link, Username;
    Toolbar mActionBarToolbar;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayShowCustomEnabled(true);

        imageView = (ImageView)findViewById(R.id.user_image_header);
        Username = (TextView)findViewById(R.id.username);
        Link = (TextView)findViewById(R.id.link);

        String username = getIntent().getExtras().getString("login");
        String avatarUrl = getIntent().getExtras().getString("avatar_url");
        String link = getIntent().getExtras().getString("html_url");

        Link.setText(link);
        Linkify.addLinks(Link, Linkify.WEB_URLS);

        Username.setText(username);
        Glide.with(this)
                .load(avatarUrl)
                .placeholder(R.drawable.images)
                .into(imageView);

        getSupportActionBar().setTitle("Details Activity");
    }
    private Intent createShareIntent(){
        String username = getIntent().getExtras().getString("login");
        String link = getIntent().getExtras().getString("link");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this awesome developer")
                .getIntent();
        return shareIntent;
    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_item_share);
        menuItem.setIntent(createShareIntent());
        return  true;
    }
}
