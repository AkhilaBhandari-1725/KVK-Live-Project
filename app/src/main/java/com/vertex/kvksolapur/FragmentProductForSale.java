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

public class FragmentProductForSale extends Fragment {

    GridView grd;
    ArrayList<String> category=new ArrayList<>();
    ArrayList<Integer> crops_id=new ArrayList<>();
    String url= config.url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_product_for_sale,container,false);
        grd=root.findViewById(R.id.grid_prod_for_sale);

        final Context context=getActivity().getApplicationContext();

        RequestQueue req= Volley.newRequestQueue(context);


        JsonArrayRequest jar=new JsonArrayRequest(url+"fetchProductForSale.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    JSONObject job = response.getJSONObject(0);

                    CategoryInfo.data.clear();

                    for(int i=1;i<response.length();i++){

                        JSONObject temp=response.getJSONObject(i);

                        int prod_id=temp.getInt("prod_id");
                        String prod_name=temp.getString("prod_name");
                        String prod_image=temp.getString("prod_image");

                        CategoryInfo ci=new CategoryInfo();
                        ci.setCategoryName(prod_name);
                        ci.setImage(prod_image);
                        CategoryInfo.data.add(ci);

                        category.add(prod_name);
                        crops_id.add(prod_id);

                    }

                    CategoryInfoAdapter adpt=new CategoryInfoAdapter(context);
                    grd.setAdapter(adpt);

                    grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Intent in=new Intent(context,SingleInfoActivity.class);
                            in.putExtra("category",category.get(i));
                            in.putExtra("flag","Production For Sale");
                            in.putExtra("id",crops_id);
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


        return root;
    }
}
