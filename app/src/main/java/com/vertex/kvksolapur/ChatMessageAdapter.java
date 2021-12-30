package com.vertex.kvksolapur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class ChatMessageAdapter extends BaseAdapter {

    Context context;

    public ChatMessageAdapter(Context context) {

        this.context=context;
    }

    @Override
    public int getCount() {
        return ChatMessage.data.size();
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
        view=inflater.inflate(R.layout.chat_adapter,null);

        TextView msg=view.findViewById(R.id.txt_message);
        FrameLayout fr=view.findViewById(R.id.frame_image);
        ImageView img=view.findViewById(R.id.img_chat);

        String ms[]=ChatMessage.data.get(i).getMessage();
       /* Bitmap b=ChatMessage.data.get(i).getImage();
        img.setImageBitmap(b);*/

        for(int str=0;str<ms.length;str++) {
            msg.setText(ms[str]);
        }

        return view;
    }
}
