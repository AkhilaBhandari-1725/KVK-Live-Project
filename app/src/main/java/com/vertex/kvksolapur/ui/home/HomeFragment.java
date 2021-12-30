package com.vertex.kvksolapur.ui.home;

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
import com.vertex.kvksolapur.CategoryActivity;
import com.vertex.kvksolapur.HomeCategory;
import com.vertex.kvksolapur.HomeCategoryAdapter;
import com.vertex.kvksolapur.R;
import com.vertex.kvksolapur.config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

   // private HomeViewModel homeViewModel;
    GridView grd;

    ArrayList<String> Labels=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        grd=root.findViewById(R.id.grid_demo);

        RequestQueue req=Volley.newRequestQueue(this.getActivity().getApplicationContext());

        String url= config.url;

        JsonArrayRequest jr=new JsonArrayRequest(url+"fetchCategory.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    JSONObject obj = response.getJSONObject(0);

                    HomeCategory.data.clear();
                    for (int i = 1; i < response.length(); i++) {
                        JSONObject temp = response.getJSONObject(i);

                        String head = temp.getString("CategoryHeading");
                        String catg=temp.getString("Category");
                        String img=temp.getString("Image");

                        HomeCategory hc=new HomeCategory();
                        hc.setImage(img);
                        hc.setLabel(head);
                        HomeCategory.data.add(hc);

                        Labels.add(head);

                    }

                    HomeCategoryAdapter adpt=new HomeCategoryAdapter(getActivity().getApplicationContext());
                    grd.setAdapter(adpt);


                    grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Intent categoryIntent=new Intent(getActivity().getApplicationContext(), CategoryActivity.class);
                            categoryIntent.putExtra("id",i);
                            categoryIntent.putExtra("heading",Labels.get(i));
                            startActivity(categoryIntent);
                        }
                    });

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

        return root;
    }
}