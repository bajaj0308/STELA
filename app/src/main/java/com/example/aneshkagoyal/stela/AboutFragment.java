package com.example.aneshkagoyal.stela;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class AboutFragment extends Fragment {

    public AboutFragment() {
        // Required empty public constructor
    }

    ImageView imageView;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference first = databaseReference.child("Courses").child("IoT").child("Experiment-1").child("About");

    //  @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.root_view_file_image, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.DiagramFragment_ImageView);

        //TODO : set image for imageview by fetching data from firebase
        //Creating connection with database
        first.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String link = dataSnapshot.getValue(String.class);
                Picasso.get().load(link).into(imageView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // return super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }
}






//    TextView textView;
//    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    private DatabaseReference databaseReference = firebaseDatabase.getReference();
//    private DatabaseReference first = databaseReference.child("Courses").child("IoT").child("Experiment-1").child("About");
//
//    //  @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.root_view_file,container,false);
//        textView = (TextView) rootView.findViewById(R.id.aboutAndProgramFragment_TextView);
//
//        //TODO : set text for textview by fetching data from firebase
//        //Creating connection with database
//        first.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String link = dataSnapshot.getValue(String.class);
//                Picasso.get().load(link).into((Target) textView);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//       // return super.onCreateView(inflater, container, savedInstanceState);
//        return rootView;
//    }
//}
