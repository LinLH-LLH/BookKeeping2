package com.example.bookkeeping;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookkeeping.DataBase.MyBookKeepDBManage;
import com.example.bookkeeping.Diaglog.UpdateItemDialogFragment;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Money>ListMoney;
    private MyBookKeepDBManage manage;
    private FragmentManager fragmentManager;
    private RecyclerView recyclerView;
    public MyRecyclerViewAdapter(Context context, List<Money> ListMoney, MyBookKeepDBManage manage, FragmentManager fragmentManager, RecyclerView recyclerView){
        this.context=context;
        this.ListMoney=ListMoney;
        this.manage=manage;
        this.fragmentManager=fragmentManager;
        this.recyclerView=recyclerView;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title,subtitle,type,date;
        private ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment fragment;
                    Bundle bundle=new Bundle();
                    bundle.putInt("_id",ListMoney.get(getAdapterPosition()).get_id());
                    bundle.putString("title",ListMoney.get(getAdapterPosition()).getTitle());
                    bundle.putString("subtitle",ListMoney.get(getAdapterPosition()).getSubtitle());
                    bundle.putString("type",ListMoney.get(getAdapterPosition()).getType());
                    fragment=new UpdateItemDialogFragment(manage,recyclerView);
                    fragment.setArguments(bundle);
                    fragment.show(fragmentManager,"UpdateItemDialogFragment");
                    notifyItemRangeChanged(getAdapterPosition(), getItemCount());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(context).setTitle("刪除_id=" + ListMoney.get(getAdapterPosition()).get_id() + "資料?")
                                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    controlDB.delete(companyDataList.get(getAdapterPosition()).get_id());
                                    manage.deleteData("BOOKKEEP",ListMoney.get(getAdapterPosition()).get_id());
                                    int position = getAdapterPosition();

                                    if (position != getItemCount()){
                                        ListMoney.remove(position);
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(position, getItemCount());
                                    }
                                    Toast.makeText(context, "刪除成功", Toast.LENGTH_SHORT).show();

                                }
                            }).setNegativeButton("取消", null).create().show();
                    return false;
                }
            });
            title=(TextView)itemView.findViewById(R.id.title);
            subtitle=(TextView)itemView.findViewById(R.id.subtitle);
            type=(TextView)itemView.findViewById(R.id.type);
            img=(ImageView)itemView.findViewById(R.id.img);
            date=(TextView)itemView.findViewById(R.id.date);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        載入ViewHolder的view
        View view= LayoutInflater.from(context).inflate(R.layout.recyclerview_item2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //        設定ViewHolder的物件
        holder.img.setImageResource(ListMoney.get(position).getImg());
        holder.title.setText(ListMoney.get(position).getTitle());
        holder.subtitle.setText("$"+ListMoney.get(position).getSubtitle());
        holder.type.setText(ListMoney.get(position).getType());
        holder.date.setText(ListMoney.get(position).getYear()
                +"/"+ListMoney.get(position).getMonth()
                +"/"+ListMoney.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return ListMoney.size();
    }


}
