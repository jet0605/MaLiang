package com.example.aiiage.drawerlayoutdemo.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v4.widget.ViewDragHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.aiiage.drawerlayoutdemo.R;
import com.example.aiiage.drawerlayoutdemo.model.ContentModel;

import java.util.List;

public class ContentAdapter extends BaseAdapter{
    private Context context;
    private List<ContentModel> list;
    public ContentAdapter(Context context, List<ContentModel> list){
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(list != null){
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        ViewHold hold;
        if(contentView == null){
            hold = new ViewHold();
            contentView = LayoutInflater.from(context).inflate(R.layout.content_item,null);
            contentView.setTag(hold);
        }else{
            hold = (ViewHold)contentView.getTag();
        }
        hold.imageView = (ImageView)contentView.findViewById(R.id.item_imageview);
        hold.textView = (TextView)contentView.findViewById(R.id.item_textview);
        hold.imageView.setImageResource(list.get(position).getImageView());
        hold.textView.setText(list.get(position).getText());
        return contentView;
    }
    class ViewHold{
        public ImageView imageView;
        public TextView textView;
    }
}
