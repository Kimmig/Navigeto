package com.navigeto.navigeto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class ViewImageActivity extends AppCompatActivity {

    private NetworkImageView imageView;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        imageView = (NetworkImageView) findViewById(R.id.imageView);
        loadImage(359); //UID passed will be on request.
    }

    private void loadImage(int User_ID){
String url = MainActivity.masterTable.getMasterTable().get(User_ID).getUserPicPathServer();
        // Code to get URL and nickname from JSON
        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.drawable.image, android.R.drawable
                        .ic_dialog_alert));
        imageView.setImageUrl(url, imageLoader);

//        Glide.with(this).load(MainActivity.masterTable.getMasterTable().get(User_ID).getLoRes()).into(imageView);

    }
}