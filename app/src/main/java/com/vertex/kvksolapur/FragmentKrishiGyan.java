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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentKrishiGyan extends Fragment {

    String krishi_flag=SubCategoryFragmentsActivity.fragmentsFlag;
    GridView grd;
    String url= config.url;
    ArrayList<String> Category_name=new ArrayList<>();
    ArrayList<Integer> Category_id=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_krishi_gyan,container,false);

        grd=root.findViewById(R.id.grid_krishi_gyan);
        final Context context=getActivity().getApplicationContext();

        RequestQueue req= Volley.newRequestQueue(context);

        if(krishi_flag.equals("Animals")){

            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchAnimals.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        CategoryInfo.data.clear();

                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);

                            int anim_id=temp.getInt("animal_id");
                            String animal_name=temp.getString("animal_name");
                            String animal_image=temp.getString("animal_image");

                            CategoryInfo ci=new CategoryInfo();
                            ci.setCategoryName(animal_name);
                            ci.setImage(animal_image);
                            CategoryInfo.data.add(ci);

                            Category_name.add(animal_name);
                            Category_id.add(anim_id);

                            Toast.makeText(context,"fragment animals",Toast.LENGTH_LONG).show();

                        }

                        CategoryInfoAdapter adpt=new CategoryInfoAdapter(context);
                        grd.setAdapter(adpt);

                        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent in=new Intent(context,SingleInfoActivity.class);
                                in.putExtra("category",Category_name.get(i));
                                in.putExtra("flag",krishi_flag);
                                in.putExtra("id",Category_id.get(i));
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

       if(krishi_flag.equals("Vegetables")){

            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchVeges.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        CategoryInfo.data.clear();

                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);


                            int veg_id=temp.getInt("veg_id");
                            String vname=temp.getString("veg_name");
                            String vimage=temp.getString("veg_img");

                            CategoryInfo ci=new CategoryInfo();
                            ci.setCategoryName(vname);
                            ci.setImage(vimage);
                            CategoryInfo.data.add(ci);

                            Category_id.add(veg_id);
                            Category_name.add(vname);

                            Toast.makeText(context,"fragment veges",Toast.LENGTH_LONG).show();

                        }

                        CategoryInfoAdapter adpt=new CategoryInfoAdapter(context);
                        grd.setAdapter(adpt);

                        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent in=new Intent(context,SingleInfoActivity.class);
                                in.putExtra("category",Category_name.get(i));
                                in.putExtra("flag",krishi_flag);
                                in.putExtra("id",Category_id.get(i));
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

        if(krishi_flag.equals("Fruites")){

            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchFruits.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        CategoryInfo.data.clear();

                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);


                            int fid=temp.getInt("fruit_id");
                            String fname=temp.getString("fruit_name");
                            String fimage=temp.getString("fruit_img");

                            CategoryInfo ci=new CategoryInfo();
                            ci.setCategoryName(fname);
                            ci.setImage(fimage);
                            CategoryInfo.data.add(ci);

                            Category_id.add(fid);
                            Category_name.add(fname);

                            Toast.makeText(context,"fragment fruit",Toast.LENGTH_LONG).show();

                        }

                        CategoryInfoAdapter adpt=new CategoryInfoAdapter(context);
                        grd.setAdapter(adpt);

                        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent in=new Intent(context,SingleInfoActivity.class);
                                in.putExtra("category",Category_name.get(i));
                                in.putExtra("flag",krishi_flag);
                                in.putExtra("id",Category_id.get(i));
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

        if(krishi_flag.equals("Flowers")){

            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchFlowers.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        CategoryInfo.data.clear();

                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);

                            int flwr_id=temp.getInt("flower_id");
                            String flwr_name=temp.getString("flower_name");
                            String flwr_image=temp.getString("flower_img");

                            CategoryInfo ci=new CategoryInfo();
                            ci.setCategoryName(flwr_name);
                            ci.setImage(flwr_image);
                            CategoryInfo.data.add(ci);

                            Category_id.add(flwr_id);
                            Category_name.add(flwr_name);

                            Toast.makeText(context,"fragment flower",Toast.LENGTH_LONG).show();

                        }

                        CategoryInfoAdapter adpt=new CategoryInfoAdapter(context);
                        grd.setAdapter(adpt);

                        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent in=new Intent(context,SingleInfoActivity.class);
                                in.putExtra("category",Category_name.get(i));
                                in.putExtra("flag",krishi_flag);
                                in.putExtra("id",Category_id.get(i));
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
        if(krishi_flag.equals("Agricultural Processing & value addition")){

            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchProcessValue.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        CategoryInfo.data.clear();

                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);

                            int process_id=temp.getInt("process_id");
                            String process_name=temp.getString("process_name");
                            String process_img=temp.getString("process_img");

                            CategoryInfo ci=new CategoryInfo();
                            ci.setCategoryName(process_name);
                            ci.setImage(process_img);
                            CategoryInfo.data.add(ci);

                            Category_name.add(process_name);
                            Category_id.add(process_id);

                            Toast.makeText(context,"fragment Processing",Toast.LENGTH_LONG).show();


                        }

                        CategoryInfoAdapter adpt=new CategoryInfoAdapter(context);
                        grd.setAdapter(adpt);

                        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent in=new Intent(context,SingleInfoActivity.class);
                                in.putExtra("category",Category_name.get(i));
                                in.putExtra("flag",krishi_flag);
                                in.putExtra("id",Category_id.get(i));
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
        if(krishi_flag.equals("Success Stories")){

            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchSuccessStory.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        CategoryInfo.data.clear();
                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);

                            int story_id=temp.getInt("story_id");
                            String story_name=temp.getString("story_name");
                            String story_img=temp.getString("story_img");

                            CategoryInfo ci=new CategoryInfo();
                            ci.setCategoryName(story_name);
                            ci.setImage(story_img);
                            CategoryInfo.data.add(ci);

                            Category_id.add(story_id);
                            Category_name.add(story_name);

                            Toast.makeText(context,"fragment Success Story",Toast.LENGTH_LONG).show();

                        }

                        CategoryInfoAdapter adpt=new CategoryInfoAdapter(context);
                        grd.setAdapter(adpt);

                        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent in=new Intent(context,SingleInfoActivity.class);
                                in.putExtra("category",Category_name.get(i));
                                in.putExtra("flag",krishi_flag);
                                in.putExtra("id",Category_id.get(i));
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

        return  root;
    }
}
