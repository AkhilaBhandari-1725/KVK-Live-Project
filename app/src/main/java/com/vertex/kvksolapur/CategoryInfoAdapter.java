package com.vertex.kvksolapur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CategoryInfoAdapter extends BaseAdapter {

    Context context;

    public  CategoryInfoAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return CategoryInfo.data.size();
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

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.category_info_adapter,null);

         ImageView img_one=view.findViewById(R.id.img_category_one);
        TextView txt_nm=view.findViewById(R.id.img_category_nm);

        String img=CategoryInfo.data.get(i).getImage();
        String url= config.url;

        String imageUri=url+img;
        Picasso.with(context).load(imageUri).fit().centerCrop().into(img_one);

        txt_nm.setText(CategoryInfo.data.get(i).getCategoryName());

        return view;
    }
}
