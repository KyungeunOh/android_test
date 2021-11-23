package com.opensource.firebasetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<EcoPlace> arrayList;
    private Context context;
    //어댑터에서 액티비티 액션을 가져올 때 context가 필요한데 어댑터에는 context가 없다.
    //선택한 액티비티에 대한 context를 가져올 때 필요하다.

    public CustomAdapter(ArrayList<EcoPlace> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    //실제 리스트뷰가 어댑터에 연결된 다음에 뷰 홀더를 최초로 만들어낸다.
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ecoplace, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImg())
                .into(holder.iv_img);
        holder.tv_name.setText(arrayList.get(position).getName());
        holder.tv_addr.setText(arrayList.get(position).getAddr());
        holder.tv_number.setText(arrayList.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
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
    }
}