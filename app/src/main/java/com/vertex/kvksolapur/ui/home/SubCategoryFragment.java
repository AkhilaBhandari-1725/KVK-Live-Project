package com.vertex.kvksolapur.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.vertex.kvksolapur.HomeCategory;
import com.vertex.kvksolapur.HomeCategoryAdapter;
import com.vertex.kvksolapur.R;
import com.vertex.kvksolapur.SubCategoryAdapter;

public class SubCategoryFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_subcategory, container, false);

        return root;
         }

    }

