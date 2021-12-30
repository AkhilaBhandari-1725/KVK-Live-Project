package com.vertex.kvksolapur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Locale;

public class SingleInfoActivity extends AppCompatActivity {

    TextView heading,info,txt_pdf;
    ImageView Image_view;
    int crop_id;
    String category,flag,pdf,pdf_name="KVKSoalpur_Crops.pdf";
    String url= config.url;
    String uri;
    ArrayList<String> pname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_info);

        heading=findViewById(R.id.txt_label);
        info=findViewById(R.id.txt_info);
        Image_view=findViewById(R.id.img_vw_info);
        txt_pdf=findViewById(R.id.txt_pdf);


        getSupportActionBar().hide();

        Intent in=getIntent();
        flag=in.getStringExtra("flag");
        category=in.getStringExtra("category");
        crop_id=in.getIntExtra("id",0);

        Toast.makeText(getApplicationContext(),crop_id+" "+category, Toast.LENGTH_SHORT).show();

        //fetch values from main crops using id n display all records i.e set to heading, image, discription

        RequestQueue req= Volley.newRequestQueue(getApplicationContext());

        uri+=url+"upload/";

        if(flag.equals("Main Crops")) {

            JsonArrayRequest jar = new JsonArrayRequest(url + "maincrops.php?id=" + crop_id , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject temp = response.getJSONObject(0);

                        String name = temp.getString("maincrops_name");
                        String image = temp.getString("img_src");
                        String disc = temp.getString("maincrops_description");
                         pdf = temp.getString("maincrops_pdf");

                         uri+=image;
                         Picasso.with(getApplicationContext()).load(uri).into(Image_view);

                        heading.setText(name);
                        // image.setImageBitmap(image);

                        info.setText(disc);
                        //txt_pdf.setText(pdf);


                    } catch (JSONException e) {

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

        if(flag.equals("Nagadi Crops")) {

            JsonArrayRequest jar = new JsonArrayRequest(url + "nagadicrop.php?id=" + crop_id , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject temp = response.getJSONObject(0);

                        String name = temp.getString("nagadicrop_name");
                        String image = temp.getString("img_src");
                        String disc = temp.getString("nagadicrop_description");
                       pdf = temp.getString("nagcrops_pdf");

                        heading.setText(name);
                        uri+=image;
                        Picasso.with(getApplicationContext()).load(uri).into(Image_view);
                        info.setText(disc);
                       // txt_pdf.setText(pdf);


                    } catch (JSONException e) {

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

        if(flag.equals("Emergency Crop")) {

            JsonArrayRequest jar = new JsonArrayRequest(url + "emergency.php?id=" + crop_id , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject temp = response.getJSONObject(0);

                        String name = temp.getString("crop_name");
                        String image = temp.getString("img_src");
                        String disc = temp.getString("crop_description");
                         pdf = temp.getString("crop_pdf");

                        heading.setText(name);
                        uri+=image;
                        Picasso.with(getApplicationContext()).load(uri).into(Image_view);
                        info.setText(disc);
                       // txt_pdf.setText(pdf);


                    } catch (JSONException e) {

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

        if(flag.equals("Animals")) {

            JsonArrayRequest jar = new JsonArrayRequest(url + "animal.php?id=" + crop_id , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject temp = response.getJSONObject(0);

                        String name = temp.getString("animal_name");
                        String image = temp.getString("img_animal");
                        String disc = temp.getString("animal_description");
                         pdf = temp.getString("animal_pdf");

                        heading.setText(name);
                        uri+=image;
                        Picasso.with(getApplicationContext()).load(uri).into(Image_view);
                        info.setText(disc);
                        //txt_pdf.setText(pdf);


                    } catch (JSONException e) {

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

        if(flag.equals("Fruites")) {

            JsonArrayRequest jar = new JsonArrayRequest(url + "fruits.php?id=" + crop_id , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject temp = response.getJSONObject(0);

                        CategoryInfo.data.clear();

                        String name = temp.getString("fruit_name");
                        String image = temp.getString("img_fruit");
                        String disc = temp.getString("fruit_description");
                         pdf = temp.getString("fruit_pdf");

                        heading.setText(name);
                        uri+=image;
                        Picasso.with(getApplicationContext()).load(uri).into(Image_view);
                        info.setText(disc);
                        //txt_pdf.setText(pdf);


                    } catch (JSONException e) {

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

        if(flag.equals("Vegetables")) {

            JsonArrayRequest jar = new JsonArrayRequest(url + "vegetable.php?id=" + crop_id , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject temp = response.getJSONObject(0);

                        String name = temp.getString("veg_name");
                        String image = temp.getString("img_veg");
                        String disc = temp.getString("veg_description");
                         pdf = temp.getString("veg_pdf");

                        heading.setText(name);
                        uri+=image;
                        Picasso.with(getApplicationContext()).load(uri).into(Image_view);
                        info.setText(disc);
                        //txt_pdf.setText(pdf);


                    } catch (JSONException e) {

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

        if(flag.equals("Success Stories")) {

            JsonArrayRequest jar = new JsonArrayRequest(url + "success.php?id=" + crop_id , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject temp = response.getJSONObject(0);

                        String name = temp.getString("name");
                        String image = temp.getString("image");
                        String disc = temp.getString("description");
                         pdf = temp.getString("pdf");

                        heading.setText(name);
                        uri+=image;
                        Picasso.with(getApplicationContext()).load(uri).into(Image_view);
                        info.setText(disc);
                        //txt_pdf.setText(pdf);


                    } catch (JSONException e) {

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



        if(flag.equals("Flowers")) {

            JsonArrayRequest jar = new JsonArrayRequest(url + "flower.php?id=" + crop_id , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject temp = response.getJSONObject(0);

                        String name = temp.getString("flower_name");
                        String image = temp.getString("img_flower");
                        String disc = temp.getString("flower_description");
                         pdf = temp.getString("flower_pdf");

                        heading.setText(name);
                        uri+=image;
                        Picasso.with(getApplicationContext()).load(uri).into(Image_view);
                        info.setText(disc);
                        //txt_pdf.setText(pdf);


                    } catch (JSONException e) {

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

        if(flag.equals("Agricultural Processing & value addition")) {

            JsonArrayRequest jar = new JsonArrayRequest(url + "process.php?id=" + crop_id , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject temp = response.getJSONObject(0);

                        String name = temp.getString("process_name");
                        String image = temp.getString("img_pro");
                        String disc = temp.getString("process_description");
                         pdf = temp.getString("process_pdf");

                        heading.setText(name);
                        uri+=image;
                        Picasso.with(getApplicationContext()).load(uri).into(Image_view);
                        info.setText(disc);
                        //txt_pdf.setText(pdf);


                    } catch (JSONException e) {

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

        if(flag.equals("Market")) {

            JsonArrayRequest jar = new JsonArrayRequest(url + "market.php?id=" + crop_id , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject temp = response.getJSONObject(0);

                        String name = temp.getString("market_name");
                        String image = temp.getString("Image");
                        String disc = temp.getString("description");
                        pdf = temp.getString("market_pdf");

                        heading.setText(name);
                        uri+=image;
                        Picasso.with(getApplicationContext()).load(uri).into(Image_view);
                        info.setText(disc);
                        //txt_pdf.setText(pdf);


                    } catch (JSONException e) {

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

        if(flag.equals("Production For Sale")) {

            JsonArrayRequest jar = new JsonArrayRequest(url + "product.php?id=" + crop_id , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject temp = response.getJSONObject(0);

                        String name = temp.getString("prod_name");
                        String image = temp.getString("img_prod");
                        String disc = temp.getString("prod_description");
                         pdf = temp.getString("prod_pdf");

                        heading.setText(name);
                        uri+=image;
                        Picasso.with(getApplicationContext()).load(uri).into(Image_view);
                        info.setText(disc);
                        //txt_pdf.setText(pdf);


                    } catch (JSONException e) {

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

        if(flag.equals("news articles")) {

            JsonArrayRequest jar = new JsonArrayRequest(url + "news.php?id=" + crop_id , new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    try {
                        JSONObject temp = response.getJSONObject(0);

                        String name = temp.getString("news_name");
                        String image = temp.getString("img_src");
                        String disc = temp.getString("news_description");
                         pdf = temp.getString("news_pdf");

                        heading.setText(name);
                        uri+=image;
                        Picasso.with(getApplicationContext()).load(uri).into(Image_view);
                        info.setText(disc);
                        //txt_pdf.setText(pdf);


                    } catch (JSONException e) {

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

        txt_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 pname=new ArrayList<>();

                RequestQueue req=Volley.newRequestQueue(getApplicationContext());

                JsonArrayRequest jar = new JsonArrayRequest(url + "fetchPdf.php?id=" , new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            JSONObject temp = response.getJSONObject(0);

                            for(int i=1;i<response.length();i++) {

                                String pdf_id = temp.getString("id");
                                String pdf_src = temp.getString("pdf_src");
                                String pdf_name = temp.getString("pdf_name");

                                pname.add(pdf_src);
                            }

                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                req.add(jar);

                String format = "https://drive.google.com/viewerng/viewer?embedded=true";
                String fullPath = String.format(Locale.ENGLISH, format, url+"upload/"+pname.get(1));
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fullPath));
                startActivity(browserIntent);

            }
        });

    }
}