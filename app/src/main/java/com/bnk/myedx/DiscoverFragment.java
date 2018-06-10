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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class DiscoverFragment extends Fragment {
    private EditText mSearchField;
    private Button mSearchBtn;
    private ProgressBar mProgressBar;
    private RecyclerView mResultList;
    private TextView mTextView;

   // private DatabaseReference mUserDatabase;
   private DatabaseReference mUserDatabase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_discover, container, false);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("courses");

        mSearchField=rootView.findViewById(R.id.search_courses);
        mSearchBtn=rootView.findViewById(R.id.search_button);
        mProgressBar=rootView.findViewById(R.id.progress_bar);
        mTextView=rootView.findViewById(R.id.search_for_courses);
        mResultList = rootView.findViewById(R.id.recycler_view1);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(getContext()));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }
        });





        return rootView;
    }

    private void firebaseUserSearch(String searchText) {

        Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");


        FirebaseRecyclerAdapter<CourseData, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CourseData, UsersViewHolder>(

                CourseData.class,
                R.layout.simple_list_item,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, CourseData model, int position) {
                mProgressBar.setVisibility(View.INVISIBLE);
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

        public void setDetails(Context ctx, String about, String company, String image,String name){

            TextView courseName = (TextView) mView.findViewById(R.id.course_name);
            ImageView user_image =  mView.findViewById(R.id.course_image);
            TextView companyName =mView.findViewById(R.id.course_company);

            companyName.setText(company);

            courseName.setText(name);

            Glide.with(ctx).load(image).into(user_image);


        }
    }
}

