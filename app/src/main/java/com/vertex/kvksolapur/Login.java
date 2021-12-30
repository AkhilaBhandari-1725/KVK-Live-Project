package com.vertex.kvksolapur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vertex.kvksolapur.ui.home.StartFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    EditText edt_nm,edt_ps;
    Button btn_log;
    TextView txt_acc;

    String unm,password,regexUnm,regexPassword;

    String url= config.url;

    RequestQueue req;

   public static String userNm;
    public static int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        edt_nm=findViewById(R.id.edt_mob);
        edt_ps=findViewById(R.id.edt_pass);

        btn_log=findViewById(R.id.btn_log);

        txt_acc=findViewById(R.id.txt_create_acc);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                unm=edt_nm.getText().toString();
                password=edt_ps.getText().toString();


                //regexUnm="[A-Za-z.]{1,}";
                regexPassword="[A-Za-z0-9.]{8,16}";

                if(unm.equals("") || password.equals("") ){

                    Toast.makeText(getApplicationContext(),"Enter Username and Password !! ",Toast.LENGTH_LONG).show();
                }
                else {
                    if(isUserValid(unm)) {

                        if(isPasswordValid(regexPassword,password)) {

                            req= Volley.newRequestQueue(getApplicationContext());

                                    JsonArrayRequest jar=new JsonArrayRequest(url + "userlogin.php?uid="+unm+"&ps="+password, new Response.Listener<JSONArray>() {
                                        @Override
                                        public void onResponse(JSONArray response) {

                                            try {
                                                    JSONObject temp = response.getJSONObject(0);

                                                    userId=temp.getInt("id");
                                                    userNm=temp.getString("fname");

                                                Intent i = new Intent(getApplicationContext(), StartActivity.class);
                                                i.putExtra("flag", "startOrExit");
                                                finish();
                                                startActivity(i);
                                            }
                                            catch (Exception e){
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                                        }
                                    });
                            req.add(jar);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Password should be 8 characters no symbols allowed -> abcde007 ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Plz enter valid Email or Mobile", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        txt_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registration=new Intent(getApplicationContext(),RegistrationActivity.class);
                registration.putExtra("flag","success");
                finish();
                startActivity(registration);

            }
        });
    }

    public boolean isUserValid(String text){

        String regexEmail,regexMobile;

        regexMobile="[0-9]{10}";
        regexEmail="[a-z0-9]{10,30}@[a-z]{3,6}.[a-z]{3}";

        Pattern checkRegex = Pattern.compile(regexEmail);
        Pattern checkRegex1 = Pattern.compile(regexMobile);

        //Matcher regexMatcher = checkRegex.matcher(text);

        if(text.matches(regexEmail)){
            return  true;
        }
        if(text.matches(regexMobile)){
            return  true;
        }
            return false;
    }
    public boolean isPasswordValid(String regexPassword, String password) {

        Pattern checkRegex = Pattern.compile(regexPassword);

        if(password.matches(regexPassword)){
            return  true;
        }
        else {
            return false;
        }
    }
}