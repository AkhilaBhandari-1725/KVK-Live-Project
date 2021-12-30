package com.vertex.kvksolapur.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.vertex.kvksolapur.HomeCategory;
import com.vertex.kvksolapur.HomeCategoryAdapter;
import com.vertex.kvksolapur.MainActivity;
import com.vertex.kvksolapur.R;

public class StartFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);



        return view;
    }

}
