package com.vertex.kvksolapur;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class FragmentPractices extends Fragment {

    GridView grd;
    ArrayList<String> category=new ArrayList<>();
    String url= config.url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_practices, container, false);
        grd = view.findViewById(R.id.grid_pr);

        RequestQueue req= Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonArrayRequest jar=new JsonArrayRequest(url+"fetchPracticesCategory.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {

                    JSONObject job = response.getJSONObject(0);

                    HomeCategory.data.clear();
                    for(int i=1;i<response.length();i++){

                        JSONObject temp=response.getJSONObject(i);

                        String heading=temp.getString("CategoryHeading");
                        String image=temp.getString("Image");


                        HomeCategory ci=new HomeCategory();
                        ci.setLabel(heading);
                        ci.setImage(image);
                        HomeCategory.data.add(ci);

                        category.add(heading);
                    }

                    HomeCategoryAdapter adpt=new HomeCategoryAdapter(getActivity().getApplicationContext());
                    grd.setAdapter(adpt);

                    grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Intent in=new Intent(getActivity().getApplicationContext(),SubCategoryFragmentsActivity.class);

                            if(category.get(i).equals("Horticultural Crops")){

                                in.putExtra("name", "Vegetables");
                            }
                            if(category.get(i).equals("Agricultural Crops")){

                                in.putExtra("name", "Crops");
                            }

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
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        req.add(jar);
        return view;
    }
   /* public byte[] convertDrawableToBitmap(int image){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),image);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();

        return  b;
    }*/
}
