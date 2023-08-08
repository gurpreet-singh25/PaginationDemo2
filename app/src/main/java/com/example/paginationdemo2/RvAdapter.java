package com.example.paginationdemo2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.Myviewholder> {

    Context context;
    ArrayList<Result> arrayList;

    public RvAdapter(Context context, ArrayList<Result> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom, parent, false);

        return new Myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.t1.setText(arrayList.get(position).getId() + "");
        holder.t2.setText(arrayList.get(position).getAlbumId() + "");
        holder.t3.setText(arrayList.get(position).getTitle());
        String app = arrayList.get(position).getThumbnailUrl();
        Log.e("IMG", app);
        //Glide.with(context).load("https://ibb.co/LPWT7Jn").fitCenter().into(holder.iv);
        Picasso.get().load(app).into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3, t4, t5;
        ImageView iv;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.textView);
            t2 = itemView.findViewById(R.id.textView2);
            t3 = itemView.findViewById(R.id.textView3);
//            t4 = itemView.findViewById(R.id.textView4);
//            t5 = itemView.findViewById(R.id.textView5);
            iv = itemView.findViewById(R.id.imageView);


        }


    }
}



