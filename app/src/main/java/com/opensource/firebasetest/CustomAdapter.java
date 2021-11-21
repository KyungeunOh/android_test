package com.opensource.firebasetest;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.view.View;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomAdapter> {
    private
    ImageView iv_img;
    TextView tv_name;
    TextView tv_addr;
    TextView tv_number;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        this.iv_img = itemView.findViewById(R.id.iv_img);
        this.tv_name = itemView.findViewById(R.id.tv_name);
        this.tv_addr = itemView.findViewById(R.id.tv_addr);
        this.tv_number = itemView.findViewById(R.id.tv_number);
    }
    @NonNull
    @Override
    public CustomAdapter.CustomAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
