package com.vertex.kvksolapur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.util.ArrayList;

public class FragmentCrops extends Fragment {

    GridView grd;
    String url= config.url;

    ArrayList<String> CropName=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_crops,container,false);

        grd=root.findViewById(R.id.grid_crops);

        final Context context=getActivity().getApplicationContext();

        RequestQueue req= Volley.newRequestQueue(context);

        JsonArrayRequest jar=new JsonArrayRequest(url+"fetchCrops.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    JSONObject job = response.getJSONObject(0);

                    CategoryInfo.data.clear();
                    for(int i=1;i<response.length();i++){

                        JSONObject temp=response.getJSONObject(i);

                        int cid=temp.getInt("categoryid");
                        String cname=temp.getString("CategoryHeading");
                        String cimage=temp.getString("Image");

                        CategoryInfo ci=new CategoryInfo();
                        ci.setCategoryName(cname);
                        ci.setImage(cimage);
                        CategoryInfo.data.add(ci);

                        CropName.add(cname);

                        Toast.makeText(context,"fragment crops",Toast.LENGTH_LONG).show();

                    }

                    CategoryInfoAdapter adpt=new CategoryInfoAdapter(getActivity().getApplicationContext());
                    grd.setAdapter(adpt);

                    grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Intent intent=new Intent(context,SubFragmentsActivity.class);
                            intent.putExtra("category",CropName.get(i));
                            startActivity(intent);

                        }
                    });

                }
                catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        req.add(jar);
        return root;
    }
}
