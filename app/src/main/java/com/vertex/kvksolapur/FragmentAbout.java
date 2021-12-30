package com.vertex.kvksolapur;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentAbout extends Fragment {

    ImageView img;
    TextView txt;
    String url= config.url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // get the reference of Button
        img = view.findViewById(R.id.img_vw_abt);
        txt = view.findViewById(R.id.txt_info);

        RequestQueue req= Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonArrayRequest jar=new JsonArrayRequest(url+"fetchAbout.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {

                    JSONObject job = response.getJSONObject(0);

                    for(int i=1;i<response.length();i++){

                        JSONObject temp=response.getJSONObject(i);

                        String heading=temp.getString("CategoryHeading");
                        String image=temp.getString("Image");
                        String info=temp.getString("Info");

                        txt.setText(info);

                        String imageUri=url+image;

                        Picasso.with(getActivity().getApplicationContext()).load(imageUri).fit().centerCrop().into(img);

                    }

                }
                catch (JSONException e){
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        req.add(jar);

        return view;
    }

}
