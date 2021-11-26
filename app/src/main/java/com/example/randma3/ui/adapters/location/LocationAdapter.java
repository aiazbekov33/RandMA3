package com.example.randma3.ui.adapters.location;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.randma3.data.network.dtos.episode.Episode;
import com.example.randma3.data.network.dtos.location.Location;
import com.example.randma3.databinding.ItemEpisodeBinding;
import com.example.randma3.databinding.ItemLocationBinding;
import com.example.randma3.inter.OnItemClickListener;
import com.example.randma3.ui.adapters.episode.EpisodeAdapter;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder>{

    private ArrayList<Location> list = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public LocationAdapter.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationViewHolder(
                ItemLocationBinding.inflate(
                        LayoutInflater.from(parent.getContext())
                ),listener
        );
    }


    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.LocationViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void submitList (ArrayList<Location> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public static class LocationViewHolder extends RecyclerView.ViewHolder {
        private final ItemLocationBinding binding;
        private OnItemClickListener listener;


        public LocationViewHolder(@NonNull ItemLocationBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void onBind(Location location) {
            Glide.with(binding.imageLocation)
                    .load(location.getType())
                    .into(binding.imageLocation);
            binding.textLocationFullName.setText(location.getName());

            itemView.setOnClickListener(v -> listener.onItemClickListener(location.getId()));
        }
    }
    public void setOnItemClickListener (OnItemClickListener listener){
        this.listener = listener;

    }
}
