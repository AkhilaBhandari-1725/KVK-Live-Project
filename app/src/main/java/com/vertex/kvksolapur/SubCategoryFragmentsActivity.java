package com.vertex.kvksolapur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SubCategoryFragmentsActivity extends AppCompatActivity {

    TextView heading;
    String url= config.url;

    public static String fragmentsFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_fragments);

        heading=findViewById(R.id.text_head);

        getSupportActionBar().hide();
        Intent in=getIntent();
        String cid=in.getStringExtra("id");
        String category=in.getStringExtra("name");

        heading.setText(category);

        fragmentsFlag=category;

        Toast.makeText(getApplicationContext(),fragmentsFlag,Toast.LENGTH_LONG).show();

       if(category.equals("Crops")){


            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();

            ft.add(R.id.frame_three,new FragmentCrops());
            ft.commit();

        }

        if(category.equals("Animals") || category.equals("Vegetables") || category.equals("Fruites") || category.equals("Flowers") ||
                category.equals("Agricultural Processing & value addition") || category.equals("Success Stories") ){

            fragmentsFlag=category;

            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();

            ft.add(R.id.frame_three,new FragmentKrishiGyan());
            ft.commit();

        }

        if(category.equals("Organic Farming") ){

            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();

            ft.replace(R.id.frame_three,new FragmentPractices());
            ft.commit();
        }

        if(category.equals("Market")){


            String market_nm=in.getStringExtra("market_nm");
            int prod_id=in.getIntExtra("id",0);

            Intent intent=new Intent(getApplicationContext(),SingleInfoActivity.class);
            intent.putExtra("flag",category);
            intent.putExtra("id",prod_id);
            intent.putExtra("prod_nm",market_nm);
            finish();
            startActivity(intent);

        }

        if(category.equals("news articles")){

            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();

            ft.replace(R.id.frame_three,new FragmentNewsArticles());
            ft.commit();

        }

        if(category.equals("Books/Chapters")){

            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();

            ft.replace(R.id.frame_three,new FragmentPublications());
            ft.commit();

        }

        if(category.equals("Production For Sale")){

            String prod_nm=in.getStringExtra("prod_nm");
            int prod_id=in.getIntExtra("id",0);

           Intent intent=new Intent(getApplicationContext(),SingleInfoActivity.class);
           intent.putExtra("flag",category);
           intent.putExtra("id",prod_id);
           intent.putExtra("prod_nm",prod_nm);
           finish();
           startActivity(intent);

        }

    }


}