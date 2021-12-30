package com.vertex.kvksolapur;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    ListView lst_chat;
    TextView txt_expertName;
    EditText edt_message;
    ImageButton send,add;

    String url= config.url;
    Intent photoPickerIntent;
    private Bitmap currentImage;

    private ChatMessageAdapter mAdapter;
    String message[],expName[];

    RequestQueue req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().hide();

        Intent chat = getIntent();
        final String expertNm=chat.getStringExtra("expertname");
        int expertId=chat.getIntExtra("expertId",0);

        txt_expertName = findViewById(R.id.txt_expert_name);
        send = findViewById(R.id.img_btn_send);
        add= findViewById(R.id.img_btn_add);
        lst_chat = findViewById(R.id.lst_chat);
        txt_expertName.setText(expertNm);
        edt_message=findViewById(R.id.edt_message);

        req= Volley.newRequestQueue(getApplicationContext());

        Toast.makeText(getApplicationContext(),""+Login.userId,Toast.LENGTH_LONG).show();

        ChatMessage.data.clear();

        expName=expertNm.split(" ");

        JsonArrayRequest jar=new JsonArrayRequest(url + "fetchChatReplies.php?uid=" + Login.userId + "&expertName=" + expName[1], new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {

                    JSONObject ob = response.getJSONObject(0);

                    ChatMessage.data.clear();
                    for(int i=1;i<response.length();i++) {

                        JSONObject temp = response.getJSONObject(0);

                        String msg = temp.getString("message");

                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                        String[] fmsg = new String[]{msg};

                        for (int j = 0; j < fmsg.length;j++){

                            ChatMessage cm = new ChatMessage();
                            cm.setRmessage(fmsg[i]);
                            cm.data.add(cm);
                    }

                    }


                    ChatAdapter adpt=new ChatAdapter(getApplicationContext());
                    lst_chat.setAdapter(adpt);

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

       send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                message=new String[]{edt_message.getText().toString()};

                StringRequest chatInsert = new StringRequest(Request.Method.POST,url + "chatInsert.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
                    protected Map<String,String> getParams() throws AuthFailureError{

                        String image="";

                        Map<String,String> params = new HashMap<String,String>();

                        for(int i=0;i<message.length;i++){

                            ChatMessage cm=new ChatMessage();
                            cm.setMessage(message);
                            cm.data.add(cm);

                        params.put("msg",message[i]);
                        params.put("uid",""+Login.userId);
                        params.put("expertName",expertNm);
                        params.put("msgImage",image);
                        }
                        return params;
                    }
                };

                req.add(chatInsert);
                edt_message.setText(" ");

                //lst.setSelection(mAdapter.getCount()-1);

                ChatMessageAdapter adpt=new ChatMessageAdapter(getApplicationContext());
                lst_chat.setAdapter(adpt);
            }
        });

        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                String[] mimeTypes = {"image/jpeg", "image/png"};
                photoPickerIntent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
                startActivityForResult(photoPickerIntent, 1);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1){
                Bitmap bm = null;
                if (data != null) {
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                ChatMessage cm=new ChatMessage();
                cm.setImage(bm);
                cm.data.add(cm);

                //photoPickerIntent.putExtra("bitmap",bm);

            }
        }
    }
}