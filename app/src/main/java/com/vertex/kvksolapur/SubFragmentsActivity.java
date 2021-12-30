package com.vertex.kvksolapur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubFragmentsActivity extends AppCompatActivity {

    GridView grd;
    TextView heading;
    ArrayList<String> category=new ArrayList<>();
    ArrayList<Integer> crop_id=new ArrayList<>();
    String categ,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_fragments);

        grd=findViewById(R.id.grid_sub_crops);
        heading=findViewById(R.id.text_head);

        Intent intent=getIntent();
        categ=intent.getStringExtra("category");

        heading.setText(categ);

        url=config.url;

        final Context context=getApplicationContext();
        getSupportActionBar().hide();

        RequestQueue req= Volley.newRequestQueue(context);

        if(categ.equals("Main Crops")){

            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchMainCrops.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        CategoryInfo.data.clear();
                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);

                            int cid=temp.getInt("crop_id");
                            String cname=temp.getString("mcrop_name");
                            String cimage=temp.getString("mcrop_img");

                            CategoryInfo ci=new CategoryInfo();
                            ci.setCategoryName(cname);
                            ci.setImage(cimage);
                            CategoryInfo.data.add(ci);

                            category.add(cname);
                            crop_id.add(cid);

                        }

                        CategoryInfoAdapter adpt=new CategoryInfoAdapter(context);
                        grd.setAdapter(adpt);

                        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent in=new Intent(context,SingleInfoActivity.class);
                                in.putExtra("category",category.get(i));
                                in.putExtra("flag",categ);
                                in.putExtra("id",crop_id.get(i));
                                startActivity(in);
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

        }

        if(categ.equals("Nagadi Crops")){
            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchNagadiCrops.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        CategoryInfo.data.clear();

                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);

                            int cid=temp.getInt("crop_id");
                            String cname=temp.getString("ncrop_name");
                            String cimage=temp.getString("ncrop_img");

                            CategoryInfo ci=new CategoryInfo();
                            ci.setCategoryName(cname);
                            ci.setImage(cimage);
                            CategoryInfo.data.add(ci);

                            category.add(cname);
                            crop_id.add(cid);

                        }

                        CategoryInfoAdapter adpt=new CategoryInfoAdapter(getApplicationContext());
                        grd.setAdapter(adpt);

                        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent in=new Intent(context,SingleInfoActivity.class);
                                in.putExtra("category",category.get(i));
                                in.putExtra("flag",categ);
                                in.putExtra("id",crop_id.get(i));
                                startActivity(in);
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

        }

        if(categ.equals("Emergency Crop")){

            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchEmergencyCrop.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        CategoryInfo.data.clear();

                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);

                            int cid=temp.getInt("crop_id");
                            String cname=temp.getString("crop_name");
                            String cimage=temp.getString("img_src");

                            CategoryInfo ci=new CategoryInfo();
                            ci.setCategoryName(cname);
                            ci.setImage(cimage);
                            CategoryInfo.data.add(ci);

                            category.add(cname);
                            crop_id.add(cid);

                        }

                        CategoryInfoAdapter adpt=new CategoryInfoAdapter(getApplicationContext());
                        grd.setAdapter(adpt);

                        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent in=new Intent(context,SingleInfoActivity.class);
                                in.putExtra("category",category.get(i));
                                in.putExtra("flag",categ);
                                in.putExtra("id",crop_id.get(i));
                                startActivity(in);
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
        }

    }
}