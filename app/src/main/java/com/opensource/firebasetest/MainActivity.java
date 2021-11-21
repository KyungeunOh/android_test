package com.opensource.firebasetest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    private ListView listView;
    private ArrayAdapter<Object> adapter;
    List<Object> Array = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // listView = (ListView)findViewById(R.id.listviewmsg);
        TextView tname = findViewById(R.id.name);
        TextView taddr = findViewById(R.id.addr);
        TextView tnumber = findViewById(R.id.number);
        TextView timg = findViewById(R.id.img);
        final String[] imagestr = new String[1];

        initDatabase();

        adapter = new ArrayAdapter<Object>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<Object>());
        // listView.setAdapter(adapter);

        mReference = mDatabase.getReference("hotel");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.clear();

                for (DataSnapshot hotelData : snapshot.getChildren()){
                    // Object obj = hotelData.getValue();
                    for(DataSnapshot details : hotelData.getChildren()){
                        Object obj = details.getValue();
                        if(details.getKey().equals("img")){
                            imagestr[0] = details.getValue().toString();
                        }
                        Array.add(obj);
                        adapter.add(obj);
                    }
                    // Array.add(obj);
                    // adapter.add(obj);
                }

                timg.setText(imagestr[0]);


                adapter.notifyDataSetChanged();
                // listView.setSelection(adapter.getCount() - 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ImageView img_test = findViewById(R.id.img_test);

        FirebaseStorage storage = FirebaseStorage.getInstance("gs://greener-test-c9229.appspot.com/");
        // StorageReference storageRef = storage.getReference().child(imagestr[0]);
        StorageReference storageRef = storage.getReference("hotel/seamark.jpg");
        Glide.with(this).load(storageRef).into(img_test);

        /*
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).error(R.drawable.ic_launcher_foreground).into(img_test);
            }
        });
         */
        // Glide.with(this).load(storageRef).into(img_test);
        // Glide.with(MainActivity.this).load(R.drawable.ic_launcher_foreground).into(img_test);
    }

    private void initDatabase() {
        mDatabase = FirebaseDatabase.getInstance();

        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");

        mChild = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mReference.addChildEventListener(mChild);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }
}

