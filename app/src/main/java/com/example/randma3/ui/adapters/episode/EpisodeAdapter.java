package com.example.randma3.ui.adapters.episode;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.randma3.data.network.dtos.episode.Episode;
import com.example.randma3.databinding.ItemEpisodeBinding;
import com.example.randma3.inter.OnItemClickListener;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>{
    private ArrayList<Episode> list = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EpisodeViewHolder(
                ItemEpisodeBinding.inflate(
                        LayoutInflater.from(parent.getContext())
                ),listener
        );
    }


    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void submitList (ArrayList<Episode>list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class EpisodeViewHolder extends RecyclerView.ViewHolder {
        private final ItemEpisodeBinding binding;
        private OnItemClickListener listener;


        public EpisodeViewHolder(@NonNull ItemEpisodeBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void onBind(Episode episode) {
            Glide.with(binding.imageEpisode)
                    .load(episode.getType())
                    .into(binding.imageEpisode);
            binding.textEpisodeFullName.setText(episode.getName());

            itemView.setOnClickListener(v ->{
                listener.onItemClickListener(episode.getId());
            });
        }
    }
    public void setOnItemClickListener (OnItemClickListener listener){
        this.listener = listener;

    }
}

