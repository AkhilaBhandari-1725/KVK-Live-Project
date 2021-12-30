package com.vertex.kvksolapur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vertex.kvksolapur.ui.home.SubCategoryFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    GridView grd;
    TextView txt;
    RequestQueue req;
    String heading;

    ArrayList<String> category=new ArrayList<>();
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getSupportActionBar().hide();

        Intent intent=getIntent();

        grd=findViewById(R.id.grid_categories);
        txt=findViewById(R.id.text_head);

        heading=intent.getStringExtra("heading");
        txt.setText(heading);

        req= Volley.newRequestQueue(getApplicationContext());

        String url= config.url;

        if(heading.equals("Home")) {

            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchHomeCategories.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        CategoryInfo.data.clear();
                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);

                            String cheading=temp.getString("CategoryHeading");
                            String image=temp.getString("Image");

                            category.add(cheading);

                            CategoryInfo obj = new CategoryInfo();
                            obj.setImage(image);
                            obj.setCategoryName(cheading);
                            CategoryInfo.data.add(obj);

                        }
                        CategoryInfoAdapter adpt=new CategoryInfoAdapter(getApplicationContext());
                        grd.setAdapter(adpt);

                        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent intent = new Intent(getApplicationContext(), SubCategoryActivity.class);
                                intent.putExtra("position", i);
                                intent.putExtra("heading",category.get(i));
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

                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
            req.add(jar);
        }

        if(heading.equals("About")){

            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft;
            ft=fm.beginTransaction();
            ft.add(R.id.frame,new FragmentAbout());
            ft.commit();
        }

        if(heading.equals("Contact")){

            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft;
            ft=fm.beginTransaction();
            ft.replace(R.id.frame,new FragmentContact());
            ft.commit();
        }

        if(heading.equals("Practices")){

            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft;
            ft=fm.beginTransaction();
            ft.replace(R.id.frame,new FragmentPractices());
            ft.commit();

        }

    }

    @Override
    protected void onResume() {

        super.onResume();

    }
}