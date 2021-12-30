package com.vertex.kvksolapur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SingleViewActivity extends AppCompatActivity {

    String url= config.url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);

        getSupportActionBar().hide();

        Intent in = getIntent();

        ViewPager vw=findViewById(R.id.viewpager);

        // Selected image id
        int position = in.getExtras().getInt("id");

        ImageAdapter imageAdapter = new ImageAdapter(this);
        List<ImageView> images = new ArrayList<ImageView>();

        // Retrieve all the images
        for (int i = 0; i < imageAdapter.getCount(); i++) {
            ImageView imageView = new ImageView(this);

            /*Bitmap bmp= BitmapFactory.decodeFile(url+ContactData.data.get(position).getImage());
            imageView.setImageBitmap(bmp);
            imageView.setScaleType(ImageView.ScaleType.CENTER);*/

            String uri=url+"upload/"+ContactData.data.get(i).getImage();
            Picasso.with(this.getApplicationContext()).load(uri).into(imageView);
            images.add(imageView);
        }

        // Set the images into ViewPager
        ImagePagerAdapter pageradapter = new ImagePagerAdapter(images);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setAdapter(pageradapter);
        // Show images following the position
        viewpager.setCurrentItem(position);

        //ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        //imageView.setImageResource(imageAdapter.images[position]);


    }

}

