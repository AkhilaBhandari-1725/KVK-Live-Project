package com.vertex.kvksolapur;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentChatWithExperts extends Fragment {

    ListView lst;
    String url=config.url;
    RequestQueue req;
    ArrayList<String> expertName=new ArrayList<>();
    ArrayList<Integer> expertId = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_chat_with_experts,container,false);

        lst = root.findViewById(R.id.list_experts);

        Toast.makeText(getActivity().getApplicationContext(),"chat experts fragment",Toast.LENGTH_LONG).show();

        req= Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonArrayRequest jar=new JsonArrayRequest(url + "contact.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    JSONObject job = response.getJSONObject(0);

                    ContactData.data.clear();
                    for (int i = 0; i <response.length(); i++) {

                        JSONObject temp = response.getJSONObject(i);

                        int id=temp.getInt("id");
                        String name=temp.getString("name");
                        String image=temp.getString("image");
                        String discription=temp.getString("description");
                        String disc[]=discription.split("\r");

                        ContactData cd = new ContactData();
                        cd.setName(name);
                        cd.setImage(image);
                        cd.setDiscription(disc[0]);
                        ContactData.data.add(cd);

                        expertName.add(name);
                        expertId.add(id);

                    }

                    ContactAdapter contactAdapter=new ContactAdapter(getActivity().getApplicationContext());
                    lst.setAdapter(contactAdapter);

                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        String[] fmsg,expName;
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Intent chat= new Intent(getActivity().getApplicationContext(),ChatActivity.class);
                            chat.putExtra("expertname",expertName.get(i));
                            chat.putExtra("expertId",expertId.get(i));

                            startActivity(chat);
                        }
                    });
                }
                catch (Exception e){
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity().getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        req.add(jar);

        return root;
    }
}
