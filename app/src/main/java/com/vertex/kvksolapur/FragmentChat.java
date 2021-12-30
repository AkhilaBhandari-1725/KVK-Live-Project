package com.vertex.kvksolapur;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static android.app.Activity.RESULT_OK;

public class FragmentChat extends Fragment {

    private ChatMessageAdapter mAdapter;

    EditText edt_message;
    ImageButton send,add;

    String message[];
    ListView lst;

    String url= config.url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_chat,container,false);

        edt_message=view.findViewById(R.id.edt_message);
        send=view.findViewById(R.id.img_btn_send);
        add=view.findViewById(R.id.img_btn_add);
        lst=view.findViewById(R.id.lst_chat);

        mAdapter = new ChatMessageAdapter(getActivity().getApplicationContext());
        lst.setAdapter(mAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                message=new String[]{edt_message.getText().toString()};

                for(int i=0;i<message.length;i++){

                ChatMessage cm=new ChatMessage();
                cm.setMessage(message);
                cm.data.add(cm);
                }

                edt_message.setText(" ");

                //lst.setSelection(mAdapter.getCount()-1);

                ChatMessageAdapter adpt=new ChatMessageAdapter(getActivity().getApplicationContext());
                lst.setAdapter(adpt);
            }
        });


        return view;
    }


}
