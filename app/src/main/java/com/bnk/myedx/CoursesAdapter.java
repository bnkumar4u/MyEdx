package com.bnk.myedx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;


public class CoursesAdapter extends ArrayAdapter{
    private Context context;
    public CoursesAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;

        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(

                    R.layout.simple_list_item,parent,false);
        }
        return listItemView;

    }
}
