package com.bnk.myedx;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;


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


        mResultList.addOnItemTouchListener(new RecyclerTouchListener(getContext(),
                mResultList, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                TextView name= view.findViewById(R.id.course_name);
                TextView des= view.findViewById(R.id.desc);
                TextView about= view.findViewById(R.id.about);
                ImageView image=view.findViewById(R.id.course_image);

                Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
                Intent intent =new Intent(getContext(),CourseDetailActivity.class);
                intent.putExtra("name",name.getText()).putExtra("des",des.getText())
                        .putExtra("about",about.getText());
                intent.putExtra("byteArray", _bs.toByteArray());
                startActivity(intent);


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
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
                viewHolder.setDetails(getContext(),model.getAbout(),model.getCompany(),model.getImage(),model.getName(),model.getDescription());
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

        public void setDetails(Context ctx, String about, String company, String image, String name,String disc){

            TextView courseName =  mView.findViewById(R.id.course_name);
            ImageView user_image =  mView.findViewById(R.id.course_image);
            TextView companyName =  mView.findViewById(R.id.course_company);
            TextView descr = mView.findViewById(R.id.desc);
            TextView about_Text=mView.findViewById(R.id.about);
            about_Text.setText(about);
            descr.setText(disc);

            companyName.setText(company);

            courseName.setText(name);

            Glide.with(ctx).load(image).into(user_image);


        }
    }


    //////////////////////////////////////////////////
    /////////////////////////////////////////////
    ////////////////////////////////////
    ///////////////////////////
    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }


    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}

