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

public class FragmentNewsArticles extends Fragment {

    GridView grd;
    String url= config.url;
    ArrayList<String> newsname=new ArrayList<>();
    ArrayList<Integer> news_id=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_news_articles,container,false);

        grd=root.findViewById(R.id.grid_news_article);

        final Context context=getActivity().getApplicationContext();

        RequestQueue req= Volley.newRequestQueue(context);

        JsonArrayRequest jar=new JsonArrayRequest(url+"fetchNewsArticles.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    JSONObject job = response.getJSONObject(0);

                    CategoryInfo.data.clear();

                    for(int i=1;i<response.length();i++){

                        JSONObject temp=response.getJSONObject(i);

                        int newsid=temp.getInt("news_id");
                        String news_name=temp.getString("news_name");
                        String news_image=temp.getString("news_image");

                        CategoryInfo ci=new CategoryInfo();
                        ci.setCategoryName(news_name);
                        ci.setImage(news_image);
                        CategoryInfo.data.add(ci);

                        newsname.add(news_name);
                        news_id.add(newsid);

                        Toast.makeText(context,"fragment News",Toast.LENGTH_LONG).show();


                    }

                    CategoryInfoAdapter adpt=new CategoryInfoAdapter(getActivity().getApplicationContext());
                    grd.setAdapter(adpt);

                    grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent in=new Intent(context,SingleInfoActivity.class);
                            in.putExtra("category",newsname.get(i));
                            in.putExtra("flag","News");
                            in.putExtra("id",news_id.get(i));
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

        return  root;
    }
}
