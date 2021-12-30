package com.vertex.kvksolapur;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    EditText edt_nm,edt_mob,edt_eml,edt_village,edt_taluka,edt_age,edt_education,edt_password,edt_confirm_password;
    Button btn_submit;
    TextView txt_acc;
    String flag;
    String url= config.url;


    String name,mobile,email,village,taluka,age,education,password,cpassword;
    String details[];

    String[] str;
    String str1[];
    int mobLength,passLen;

    String regexName,regexMobile,regexEmail,regexVillage,regexTaluka,regexAge,regexEducation,regexPassword,regexConPassword;
    RequestQueue req;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edt_nm=findViewById(R.id.edt_name);
        edt_mob=findViewById(R.id.edt_mob);
        edt_eml=findViewById(R.id.edt_email);
        edt_village=findViewById(R.id.edt_village);
        edt_taluka=findViewById(R.id.edt_taluka);
        edt_age=findViewById(R.id.edt_age);
        edt_education=findViewById(R.id.edt_edu);
        edt_password=findViewById(R.id.edt_ps);
        edt_confirm_password=findViewById(R.id.edt_cnfrm_ps);

        btn_submit=findViewById(R.id.btn_submit);

        txt_acc=findViewById(R.id.txt_have_acc);

        Drawable d=getDrawable(R.drawable.taluka_icon);
        //d.setBounds(10,10,10,10);

        ScaleDrawable scaleDrawable = new ScaleDrawable(d ,0 ,50, 50);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=edt_nm.getText().toString();
                mobile=edt_mob.getText().toString();
                email=edt_eml.getText().toString();
                village=edt_village.getText().toString();
                taluka=edt_taluka.getText().toString();
                age=edt_age.getText().toString();
                education=edt_education.getText().toString();
                password=edt_password.getText().toString();
                cpassword=edt_confirm_password.getText().toString();

                details=new String[]{name,mobile,email,village,taluka,age,education,password,cpassword};


                mobLength=mobile.length();
                passLen=password.length();

                regexName="[A-Za-z]{1,}[ ]{1}[a-zA-Z]{1,}";
                regexMobile="[7-9]{1}[0-9]{9}";
                regexEmail="[a-z0-9]{10,30}@[a-z]{3,6}.[a-z]{3}";
                regexVillage="[A-Za-z]{1,}";
                regexTaluka="[A-Za-z]{1,}";
                regexAge="[0-9]{2}";
                regexEducation="[A-Za-z0-9]{1,}";
                regexPassword="[A-za-z0-9]{8,16}";
                regexConPassword="[A-za-z0-9]{8,16}";

                if(name.equals("") || mobile.equals("") || email.equals("") || age.equals("") || village.equals("") || taluka.equals("") || education.equals("") || password.equals("") || cpassword.equals("")){

                    Toast.makeText(getApplicationContext(),"Plz enter all fields !!",Toast.LENGTH_SHORT).show();
                }
                else {

                    if (isStringValid(regexName, name)) {

                        if (isMobileValid(regexMobile, mobile)) {

                            if (isEmailValid(regexEmail, email)) {

                                if (isStringValid(regexVillage, village)) {

                                    if (isStringValid(regexTaluka, taluka)) {

                                        if (isAgeValid(regexAge, age)) {

                                            if (isStringValid(regexEducation, education)) {

                                                if (isPasswordValid(regexPassword, password)) {

                                                    if (isPasswordValid(regexConPassword, cpassword) && password.equals(cpassword)) {

                                                        req=Volley.newRequestQueue(getApplicationContext());

                                                        StringRequest insert=new StringRequest(Request.Method.POST, url + "insertRegistration.php", new Response.Listener<String>() {
                                                            @Override
                                                            public void onResponse(String response) {

                                                                Toast.makeText(getApplicationContext(), "Registration Successfully Done !!", Toast.LENGTH_SHORT).show();
                                                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                                                finish();
                                                                startActivity(i);

                                                            }
                                                        }, new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {

                                                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        }){
                                                            protected Map<String,String> getParams() throws AuthFailureError{
                                                                Map<String,String> params=new HashMap<>();

                                                                params.put("Name",name);
                                                                params.put("Mobile",mobile);
                                                                params.put("Email",email);
                                                                params.put("Village",village);
                                                                params.put("Taluka",taluka);
                                                                params.put("Age",age);
                                                                params.put("Education",education);
                                                                params.put("Password",password);
                                                                params.put("ACTIVE","Active");

                                                                return params;
                                                            }
                                                        };
                                                        req.add(insert);

                                                    } else {
                                                        Toast.makeText(getApplicationContext(), " plz Confirm your password again", Toast.LENGTH_SHORT).show();
                                                    }

                                                } else {
                                                    Toast.makeText(getApplicationContext(), " password must have atleast 8-characters", Toast.LENGTH_SHORT).show();
                                                }

                                            } else {
                                                Toast.makeText(getApplicationContext(), "Plz Enter Valid String for education", Toast.LENGTH_SHORT).show();
                                            }

                                        } else {
                                            Toast.makeText(getApplicationContext(), "Plz Enter Valid Age", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Plz Enter Valid String for Taluka", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Plz Enter Valid String for Village", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Plz Enter Valid email id", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Plz Enter Valid mobile", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Plz Enter Valid Name", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        txt_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),Login.class);
                finish();
                startActivity(i);
            }
        });


    }

    public boolean isStringValid(String regex, String text){

        Pattern checkRegex = Pattern.compile(regex);
        //Matcher regexMatcher = checkRegex.matcher(text);

        if(text.matches(regex)){
            return  true;
        }
        else {
            return false;
        }
    }

    public boolean isMobileValid(String regexMobile, String mobile) {

        Pattern checkRegex = Pattern.compile(regexMobile);

        if(mobile.matches(regexMobile)){
            return  true;
        }
        else {
            return false;
        }
    }

    public boolean isEmailValid(String regexEmail, String email) {

        Pattern checkRegex = Pattern.compile(regexEmail);

        if(email.matches(regexEmail)){
            return  true;
        }
        else {
            return false;
        }
    }

    public boolean isAgeValid(String regexAge, String age) {

        Pattern checkRegex = Pattern.compile(regexAge);

        if(age.matches(regexAge)){
            return  true;
        }
        else {
            return false;
        }
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