package com.vertex.kvksolapur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ContactAdapter extends BaseAdapter {

    Context context;
    public ContactAdapter(Context context) {

        this.context=context;

    }

    @Override
    public int getCount() {
        return ContactData.data.size();
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

        LayoutInflater inflater=LayoutInflater.from(context);

        view = inflater.inflate(R.layout.contact_adapter,viewGroup,false);

        ImageView img=view.findViewById(R.id.contact_image);
        TextView txt=view.findViewById(R.id.contact_name);
        TextView txt_d=view.findViewById(R.id.contact_discr);

        String url=config.url;

        txt.setText(ContactData.data.get(i).getName());
        txt_d.setText(ContactData.data.get(i).getDiscription());

        String uri=url+ContactData.data.get(i).getImage();
        Picasso.with(context).load(uri).fit().centerCrop().into(img);

        return view;
    }
}
