package com.example.bookmoviestickets.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookmoviestickets.R;

import java.util.List;

public class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ViewHolder> {
    List<String> images;
    Context context;

    public ActorListAdapter(List<String> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public ActorListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View infalte = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_actors, parent, false);
        return new ViewHolder(infalte);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorListAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(images.get(position))
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.itemsImages);
        }
    }
}
