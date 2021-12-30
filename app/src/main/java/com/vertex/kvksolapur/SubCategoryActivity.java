package com.vertex.kvksolapur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import com.vertex.kvksolapur.ui.gallery.GalleryFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

public class SubCategoryActivity extends AppCompatActivity {

    GridView grd;
    String flag;
    int position;

    TextView txt;
    String hd;
    RequestQueue req;
    String url= config.url;

    ArrayList<String> category=new ArrayList<>();
    ArrayList<Integer> categoryId=new ArrayList<>();

    public static String FACEBOOK_URL = "https://www.facebook.com/kvksolapur";
    public static String FACEBOOK_PAGE_ID = "kvksolapur";

    public static String TWITTER_URL = "https://www.twitter.com/kvksolapur";
    public static String TWITTER_PAGE_ID = "kvksolapur";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        getSupportActionBar().hide();

        Intent intent=getIntent();
        position=intent.getIntExtra("position",0);
        hd=intent.getStringExtra("heading");

        grd = findViewById(R.id.grid_sub_category);
        txt=findViewById(R.id.text_head);

        txt.setText(hd);

        req= Volley.newRequestQueue(getApplicationContext());

        if(hd.equalsIgnoreCase("krishigyan")) {

                JsonArrayRequest jar=new JsonArrayRequest(url+"fetchKrishigyan.php", new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONObject job = response.getJSONObject(0);

                            HomeCategory.data.clear();
                            for(int i=1;i<response.length();i++){

                                JSONObject temp=response.getJSONObject(i);

                                String cheading=temp.getString("CategoryHeading");
                                String image=temp.getString("Image");

                                category.add(cheading);

                                HomeCategory obj = new HomeCategory();
                                obj.setImage(image);
                                obj.setLabel(cheading);
                                HomeCategory.data.add(obj);

                                flag=hd;

                            }
                            HomeCategoryAdapter adpt=new HomeCategoryAdapter(getApplicationContext());
                            grd.setAdapter(adpt);
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

        if(hd.equalsIgnoreCase("publications")) {

            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchPublications.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        HomeCategory.data.clear();
                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);

                            String cheading=temp.getString("CategoryHeading");
                            String image=temp.getString("Image");

                            category.add(cheading);

                            HomeCategory obj = new HomeCategory();
                            obj.setImage(image);
                            obj.setLabel(cheading);
                            HomeCategory.data.add(obj);

                            flag=hd;

                        }

                        HomeCategoryAdapter adpt=new HomeCategoryAdapter(getApplicationContext());
                        grd.setAdapter(adpt);

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

       if(hd.equalsIgnoreCase("Chat With Experts")) {

            flag=hd;


               FragmentManager fm=getSupportFragmentManager();
               FragmentTransaction ft= fm.beginTransaction();

               ft.add(R.id.frame_two,new FragmentChatWithExperts());
               ft.commit();

        }

       /* if(hd.equalsIgnoreCase("Weather")) {

            for (int i = 0; i < media.length; i++) {
                HomeCategory obj = new HomeCategory();
                obj.setImage(img_media[i]);
                obj.setLabel(media[i]);
                HomeCategory.data.add(obj);
            }
            flag=hd;
        }*/
        if(hd.equalsIgnoreCase("Gallery")) {

           FragmentManager fm=getSupportFragmentManager();
           FragmentTransaction ft=fm.beginTransaction();

           ft.add(R.id.frame_two,new GalleryFragment());
           ft.commit();
            flag=hd;
        }

        if(hd.equalsIgnoreCase("Market")) {

           JsonArrayRequest jar=new JsonArrayRequest(url+"fetchMarket.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        HomeCategory.data.clear();
                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);

                            int prod_id=temp.getInt("market_id");
                            String prod_name=temp.getString("market_name");
                            String prod_image=temp.getString("Image");

                            category.add(prod_name);
                            categoryId.add(prod_id);

                            HomeCategory obj = new HomeCategory();
                            obj.setImage(prod_image);
                            obj.setLabel(prod_name);
                            HomeCategory.data.add(obj);

                            flag=hd;
                        }

                        HomeCategoryAdapter adpt=new HomeCategoryAdapter(getApplicationContext());
                        grd.setAdapter(adpt);
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
        if(hd.equalsIgnoreCase("Production For Sale")) {

            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchProductForSale.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        HomeCategory.data.clear();
                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);

                            int prod_id=temp.getInt("prod_id");
                            String prod_name=temp.getString("prod_name");
                            String prod_image=temp.getString("prod_image");

                            category.add(prod_name);
                            categoryId.add(prod_id);

                            HomeCategory obj = new HomeCategory();
                            obj.setImage(prod_image);
                            obj.setLabel(prod_name);
                            HomeCategory.data.add(obj);

                            flag=hd;
                        }

                        HomeCategoryAdapter adpt=new HomeCategoryAdapter(getApplicationContext());
                        grd.setAdapter(adpt);
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

        if(hd.equalsIgnoreCase("social media")) {

            JsonArrayRequest jar=new JsonArrayRequest(url+"fetchMedia.php", new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject job = response.getJSONObject(0);

                        HomeCategory.data.clear();
                        for(int i=1;i<response.length();i++){

                            JSONObject temp=response.getJSONObject(i);
                            int media_id=temp.getInt("media_id");
                            String media_name=temp.getString("media_name");
                            String media_image=temp.getString("media_image");

                            category.add(media_name);
                            categoryId.add(media_id);

                            HomeCategory obj = new HomeCategory();
                            obj.setImage(media_image);
                            obj.setLabel(media_name);
                            HomeCategory.data.add(obj);

                            flag=hd;
                        }

                        HomeCategoryAdapter adpt=new HomeCategoryAdapter(getApplicationContext());
                        grd.setAdapter(adpt);

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

        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent in = new Intent(getApplicationContext(), SubCategoryFragmentsActivity.class);
                    in.putExtra("heading", flag);

                    position = i;

                    if (flag.equalsIgnoreCase("krishigyan")) {

                        in.putExtra("name", category.get(i));
                        startActivity(in);

                    }

                    if (hd.equalsIgnoreCase("publications")) {

                        in.putExtra("name", category.get(i));
                        startActivity(in);

                    }

                    if (hd.equalsIgnoreCase("Weather")) {

                        in.putExtra("name", "Market");
                        startActivity(in);

                    }

                    if (hd.equalsIgnoreCase("Market")) {

                        in.putExtra("id", categoryId.get(i));
                        in.putExtra("name", flag);
                        in.putExtra("market_nm", category.get(i));
                        startActivity(in);

                    }

                    if (hd.equalsIgnoreCase("Production For Sale")) {

                        in.putExtra("id", categoryId.get(i));
                        in.putExtra("name", flag);
                        in.putExtra("prod_nm", category.get(i));
                        startActivity(in);

                    }

                    if (hd.equalsIgnoreCase("social media")) {

                        if(category.get(i).equals("Facebook")){

                            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                            String facebookUrl = getFacebookPageURL(getApplicationContext());
                            facebookIntent.setData(Uri.parse(facebookUrl));
                            startActivity(facebookIntent);
                        }

                        if(category.get(i).equals("Twitter")) {

                            Intent twitter = new Intent(Intent.ACTION_VIEW);
                            twitter.setData(Uri.parse(TWITTER_URL));
                            startActivity(twitter);
                        }

                        if(category.get(i).equals("You Tube")){

                            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=-h1atKCysZc"));
                            startActivity(webIntent);
                        }

                        if(category.get(i).equals("WhatsApp groups")){

                            PackageManager packageManager = getApplicationContext().getPackageManager();
                            Intent wapp = new Intent(Intent.ACTION_VIEW);
                            String phone="9404285608";
                            String message="";

                            try {

                                String url = "https://api.whatsapp.com/send?phone="+ Integer.parseInt(phone) +"&text=" + URLEncoder.encode(message, "UTF-8");
                                wapp.setPackage("com.whatsapp");
                                wapp.setData(Uri.parse(url));
                                if (wapp.resolveActivity(packageManager) != null) {
                                    getApplicationContext().startActivity(wapp);
                                }
                            } catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }


            }
        });

    }
    public String getFacebookPageURL(Context context) {

        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    public String getTwitterPageURL(Context context) {

        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.twitter.android", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + TWITTER_URL;
            } else { //older versions of fb app
                return "fb://page/" + TWITTER_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

}