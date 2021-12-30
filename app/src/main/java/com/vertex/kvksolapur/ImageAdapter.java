package com.vertex.kvksolapur;

import android.content.Context;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
     Context mContext;
     String url=config.url;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return ContactData.data.size();
    }

    public Object getItem(int position) {
        return ContactData.data.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position,  View convertView, ViewGroup parent) {

        LayoutInflater inflater= LayoutInflater.from(mContext);

        convertView=inflater.inflate(R.layout.image_adapter,parent,false);

        final ImageView imageView = convertView.findViewById(R.id.img_gallery);
        final TextView imgName = convertView.findViewById(R.id.image_heading);


        Toast.makeText(mContext, "gallery ",Toast.LENGTH_LONG).show();

         imgName.setText(ContactData.data.get(position).getName());

        String uri=url+"upload/"+ContactData.data.get(position).getImage();
        Picasso.with(mContext).load(uri).fit().centerCrop().into(imageView);

        // text.setText(hd[position]);
        return convertView;
    }
}
