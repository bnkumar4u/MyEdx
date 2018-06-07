package com.bnk.myedx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class DiscoverFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);
        Context context=rootView.getContext();
        ArrayList obj=new ArrayList();
        obj.add(null);
        obj.add(null);
        obj.add(null);
        obj.add(null);
        obj.add(null);
        obj.add(null);
        obj.add(null);

        ListView listView=(ListView)rootView.findViewById(R.id.list_view);
        CoursesAdapter adapter=new CoursesAdapter(rootView.getContext(),obj);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getContext(),CourseDetailActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}

