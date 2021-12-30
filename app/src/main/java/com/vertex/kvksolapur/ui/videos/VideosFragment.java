package com.vertex.kvksolapur.ui.videos;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vertex.kvksolapur.ChatActivity;
import com.vertex.kvksolapur.ContactAdapter;
import com.vertex.kvksolapur.ContactData;
import com.vertex.kvksolapur.R;
import com.vertex.kvksolapur.config;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class VideosFragment extends Fragment {

    ListView lst;
    String url= config.url;
    RequestQueue req;
    ArrayList<String> VideoName=new ArrayList<>();
    ArrayList<Integer> VideoId = new ArrayList<>();
    ArrayList<String> VdoLink=new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_videos,container,false);

            lst = root.findViewById(R.id.lst_videos);

            req= Volley.newRequestQueue(getActivity().getApplicationContext());

            JsonArrayRequest jar=new JsonArrayRequest(url + "video.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        ContactData.data.clear();
                        for (int i = 0; i <response.length(); i++) {

                            JSONObject temp = response.getJSONObject(i);

                            int id=temp.getInt("id");
                            String name=temp.getString("vname");
                            String link=temp.getString("vlink");

                            ContactData cd = new ContactData();
                            cd.setName(name);
                            ContactData.data.add(cd);

                            VideoName.add(name);
                            VideoId.add(id);
                            VdoLink.add(link);

                        }

                        VideoAdapter contactAdapter=new VideoAdapter(getActivity().getApplicationContext());
                        lst.setAdapter(contactAdapter);

                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + VdoLink.get(i)));
                                startActivity(webIntent);
                                //watchYoutubeVideo(getActivity().getApplicationContext(),VdoLink.get(i));
                            }
                        });
                    }
                    catch (Exception e){
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getActivity().getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

            req.add(jar);

            return root;
    }

    /*public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }*/
}