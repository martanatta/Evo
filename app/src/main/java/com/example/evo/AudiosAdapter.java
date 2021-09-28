package com.example.evo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evo.apiShmapi.CategoryList;
import com.example.evo.apiShmapi.FavoriteAudioList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AudiosAdapter extends RecyclerView.Adapter<AudiosAdapter.MyViewHolderAudios> {
    List<FavoriteAudioList> mList;
    Context context;

    public AudiosAdapter(List<FavoriteAudioList> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolderAudios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_sounds_list_item, parent, false);

        return new MyViewHolderAudios(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudiosAdapter.MyViewHolderAudios holder, int position) {
//        holder.mButtonStatus.setOnClickListener(mList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolderAudios extends RecyclerView.ViewHolder {
        Integer mId;
        Integer mUser;
        View mButtonStatus;


        public MyViewHolderAudios(@NonNull View itemView) {
            super(itemView);
            mButtonStatus = itemView.findViewById(R.id.fav_btn);
        }
    }
}
