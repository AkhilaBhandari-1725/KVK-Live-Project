package com.vertex.kvksolapur.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
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
import com.vertex.kvksolapur.ContactData;
import com.vertex.kvksolapur.ImageAdapter;
import com.vertex.kvksolapur.R;
import com.vertex.kvksolapur.SingleViewActivity;
import com.vertex.kvksolapur.config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    ArrayList<String> imgsrc=new ArrayList<String>();
    ArrayList<Integer> imgId=new ArrayList<Integer>();
    RequestQueue req;
    String url= config.url;

    ListView grd;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        grd=root.findViewById(R.id.grid_gallery);

        //getActivity().getActionBar().hide();
        req = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonArrayRequest jr=new JsonArrayRequest(url+"fetchGallery.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    JSONObject obj = response.getJSONObject(0);

                    ContactData.data.clear();
                    for (int i = 1; i < response.length(); i++) {
                        JSONObject temp = response.getJSONObject(i);

                        int img_id=temp.getInt("id");
                        String Imgsrc = temp.getString("image_src");
                        String img=temp.getString("image_name");

                        ContactData cd=new ContactData();
                        cd.setImage(Imgsrc);
                        cd.setName(img);
                        ContactData.data.add(cd);

                        imgsrc.add(img);
                        imgId.add(img_id);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        req.add(jr);

        grd.setAdapter(new ImageAdapter(getActivity().getApplicationContext()));

        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                // Send intent to SingleViewActivity
                Intent i = new Intent(getActivity().getApplicationContext(), SingleViewActivity.class);
                // Pass image index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return root;
    }
}