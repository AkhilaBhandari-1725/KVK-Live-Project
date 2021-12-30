package com.vertex.kvksolapur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HomeCategoryAdapter extends BaseAdapter {

    Context context;
    public HomeCategoryAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return HomeCategory.data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.home_category_adapter,null);

        ImageView img=view.findViewById(R.id.img_vw_home);
        TextView txt=view.findViewById(R.id.text_home_lbl);

        String imgpath=HomeCategory.data.get(i).getImage();
        String url=config.url;

        String imageUri=url+imgpath;
        Picasso.with(context).load(imageUri).fit().centerCrop().into(img);

        txt.setText(HomeCategory.data.get(i).getLabel());

        return view;
    }
}
