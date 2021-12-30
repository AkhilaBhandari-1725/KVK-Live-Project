package com.vertex.kvksolapur.ui.videos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vertex.kvksolapur.ContactData;
import com.vertex.kvksolapur.R;
import com.vertex.kvksolapur.config;

public class VideoAdapter extends BaseAdapter {

    Context context;
    public VideoAdapter(Context context) {

        this.context=context;

    }

    @Override
    public int getCount() {
        return ContactData.data.size();
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

        LayoutInflater inflater=LayoutInflater.from(context);

        view = inflater.inflate(R.layout.video_adapter,viewGroup,false);

        ImageView img=view.findViewById(R.id.video_image);
        TextView txt=view.findViewById(R.id.video_name);

        String url= config.url;

        txt.setText(ContactData.data.get(i).getName());
        img.setImageResource(R.drawable.ic_baseline_video_library_24);

        return view;
    }
}

