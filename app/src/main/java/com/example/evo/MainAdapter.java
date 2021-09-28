package com.example.evo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evo.apiShmapi.CategoryList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolderMain> {
    List<CategoryList> mList;
    Context context;

    public MainAdapter(List<CategoryList> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Nullable
    @Override
    public MyViewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_main_list_item, parent, false);

        return new MyViewHolderMain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderMain holder, int position) {
        holder.mTextTitle.setText(mList.get(position).getName());
        holder.mTextDesc.setText(mList.get(position).getDescription());
        Picasso.get().load(mList.get(position).getIcon()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolderMain extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextTitle;
        TextView mTextDesc;
        Button mButton;

        public MyViewHolderMain(@NonNull View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.title_textView);
            mTextDesc = itemView.findViewById(R.id.desc_textView);
            mImageView = itemView.findViewById(R.id.imageView);
            mButton = itemView.findViewById(R.id.next_1);
        }
    }
}