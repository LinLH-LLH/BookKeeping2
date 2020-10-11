package com.example.bookkeeping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Money>ListMoney=new ArrayList<>();

    MyRecyclerViewAdapter(Context context,List<Money> ListMoney){
        this.context=context;
        this.ListMoney=ListMoney;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title,subtitle;
        private ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            subtitle=(TextView)itemView.findViewById(R.id.subtitle);
            img=(ImageView)itemView.findViewById(R.id.img);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.img.setImageResource(ListMoney.get(position).getImg());
        holder.title.setText(ListMoney.get(position).getTitle());
        holder.subtitle.setText(ListMoney.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return ListMoney.size();
    }




}
