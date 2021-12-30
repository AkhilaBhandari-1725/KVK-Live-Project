package com.vertex.kvksolapur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    ImageView img;
    String url= config.url;

    Button btn_exit,btn_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        img=findViewById(R.id.img_vw_start);
        btn_exit=findViewById(R.id.btn_exit);
        btn_continue=findViewById(R.id.btn_continue);

        getSupportActionBar().hide();

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartActivity.this.finish();
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Welcome To KVK !! ",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("flag","Home");
                finish();
                startActivity(i);
            }
        });

    }
}