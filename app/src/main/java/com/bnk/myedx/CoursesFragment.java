package com.bnk.myedx;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CoursesFragment extends Fragment {
    private RecyclerView mResultList;

    // private DatabaseReference mUserDatabase;
    private DatabaseReference mUserDatabase;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);
        mUserDatabase = FirebaseDatabase.getInstance().getReference("courses");

        progressBar =rootView.findViewById(R.id.progress_bar2);
        mResultList = rootView.findViewById(R.id.recycler_view2);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(getContext()));



        firebaseUserSearch();



        return rootView;
    }


    private void firebaseUserSearch() {

        FirebaseRecyclerAdapter<CourseData, CoursesFragment.UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CourseData, CoursesFragment.UsersViewHolder>(

                CourseData.class,
                R.layout.simple_list_item,
                CoursesFragment.UsersViewHolder.class,
                mUserDatabase

        ) {
            @Override
            protected void populateViewHolder(CoursesFragment.UsersViewHolder viewHolder, CourseData model, int position) {
                progressBar.setVisibility(View.INVISIBLE);
                viewHolder.setDetails(getContext(),model.getAbout(),model.getCompany(),model.getImage(),model.getName());
            }
        };
        mResultList.setAdapter(firebaseRecyclerAdapter);
    }

    // View Holder Class

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String about, String company, String image, String name){

            TextView courseName =  mView.findViewById(R.id.course_name);
            ImageView user_image =  mView.findViewById(R.id.course_image);
            TextView companyName =  mView.findViewById(R.id.course_company);

            companyName.setText(company);

            courseName.setText(name);

            Glide.with(ctx).load(image).into(user_image);


        }
    }
}

