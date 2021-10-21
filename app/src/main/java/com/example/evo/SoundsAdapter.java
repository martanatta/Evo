package com.example.evo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evo.apiShmapi.CategoryDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SoundsAdapter extends RecyclerView.Adapter<SoundsAdapter.MyViewHolderSounds> {
    Context mContext;
    static List<CategoryDetail.Audio> mFiles;
    private ItemClickListener mClickListener;

    public SoundsAdapter(List<CategoryDetail.Audio> mList, Context context) {
        this.mFiles = mList;
        this.mContext = context;
    }

    @Nullable
    @Override
    public MyViewHolderSounds onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.from(mContext).inflate(R.layout.activity_sounds_list_item, parent,
                false);

        return new MyViewHolderSounds(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderSounds holder, @SuppressLint("RecyclerView") int position) {
        holder.file_name.setText(mFiles.get(position).name);
        Picasso.get().load(mFiles.get(position).picture).into(holder.album_art);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(position);
            }
        });
    }
//        picasso need
//        holder.album_art.setImageResource();
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, PlayerActivity.class);
//                intent.putExtra("position", position);
//                mContext.startActivity(intent);
//            }
//        });

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        mClickListener = listener;
    }

    public class MyViewHolderSounds extends RecyclerView.ViewHolder {
        CardView mCardView;
        ImageView album_art;
        TextView file_name, listOfListeners;

        public MyViewHolderSounds(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cardViewSounds);
            album_art = itemView.findViewById(R.id.img_forest);
            file_name = itemView.findViewById(R.id.text_forest);
//            listOfListeners = itemView.findViewById(R.id.num_of_list1);
        }
    }
}