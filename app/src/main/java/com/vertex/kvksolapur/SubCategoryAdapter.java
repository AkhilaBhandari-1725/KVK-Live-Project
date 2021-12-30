package com.vertex.kvksolapur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class SubCategoryAdapter extends BaseAdapter {

    Context con;

    public SubCategoryAdapter(Context con){
        this.con=con;
    }


    @Override
    public int getCount() {
        return SubCategoryInfo.data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(con);
        view = inflater.inflate(R.layout.home_category_adapter,null);

        ImageView img=view.findViewById(R.id.img_vw_home);
        TextView txt=view.findViewById(R.id.text_home_lbl);

        //img.setImageResource(HomeCategory.data.get(i).getImage());
        txt.setText(HomeCategory.data.get(i).getLabel());

        return view;
    }
}

