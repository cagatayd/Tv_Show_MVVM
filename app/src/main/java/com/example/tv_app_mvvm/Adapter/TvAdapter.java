package com.example.tv_app_mvvm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tv_app_mvvm.Listener.OnTvItemClickListener;
import com.example.tv_app_mvvm.Model.Response.TvList;
import com.example.tv_app_mvvm.R;
import com.example.tv_app_mvvm.Utils.AppConstant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TvAdapter  extends RecyclerView.Adapter<TvAdapter.ViewHolder> {

    ArrayList<TvList>tvLists;
    Context context;
    OnTvItemClickListener listener;

    public TvAdapter(ArrayList<TvList> tvLists, OnTvItemClickListener listener, Context context) {
        this.listener = listener;
        this.tvLists = tvLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(tvLists.get(position).title);
        holder.textViewid.setText(String.valueOf(tvLists.get(position).id));

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.build().load(AppConstant.BASE_PATH_OF_IMAGE  + tvLists.get(position).poster_path).into(holder.imageView);
        holder.setOnClickListener(tvLists.get(position).id);
    }

    @Override
    public int getItemCount() {
        return tvLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView imageView;
        TextView textView;
        TextView textViewid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.poster);
            textView=itemView.findViewById(R.id.postertitle);
            textViewid=itemView.findViewById(R.id.textviewid);
        }

        private void setOnClickListener(int id) {
            itemView.setTag(id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick((int) view.getTag());
        }
    }
}
