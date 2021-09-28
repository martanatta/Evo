package com.example.evo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SoundsAdapter extends RecyclerView.Adapter<SoundsAdapter.MyViewHolderSounds> {

    static List<AudioListCategory> mFiles;
    Context mContext;

    SoundsAdapter(List<AudioListCategory> mFiles, Context mContext) {
        this.mFiles = mFiles;
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public MyViewHolderSounds onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.activity_sounds_list_item, parent, false);

        return new MyViewHolderSounds(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderSounds holder, int position) {
        holder.file_name.setText(mFiles.get(position).getTitle());
        holder.lengthOfSongs.setText(mFiles.get(position).getDuration());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PlayerActivity.class);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class MyViewHolderSounds extends RecyclerView.ViewHolder {

        ImageView album_art;
        TextView file_name, listOfListeners, lengthOfSongs;

        public MyViewHolderSounds(@NonNull View itemView) {
            super(itemView);

            album_art = itemView.findViewById(R.id.img_forest);
            file_name = itemView.findViewById(R.id.text_forest);
            listOfListeners = itemView.findViewById(R.id.num_of_list1);
//            lengthOfSongs = itemView.findViewById(R.id.songs_length);
        }
    }
}