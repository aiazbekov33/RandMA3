package com.example.randma3.ui.adapters.episode;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.randma3.data.network.dtos.episode.Episode;
import com.example.randma3.databinding.ItemEpisodeBinding;
import com.example.randma3.inter.OnItemClickListener;

public class EpisodeAdapter extends ListAdapter<Episode,EpisodeAdapter.ViewHolder>{

    public OnItemClickListener listener;

    public EpisodeAdapter() {
        super(new EpisodeDiffUtil());
    }
    
    @NonNull
    @Override
    public EpisodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static class EpisodeDiffUtil extends DiffUtil.ItemCallback<Episode> {
        @Override
        public boolean areItemsTheSame(@NonNull Episode oldItem, @NonNull Episode newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Episode oldItem, @NonNull Episode newItem) {
            return oldItem == newItem;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemEpisodeBinding binding;

        public ViewHolder(ItemEpisodeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bind(Episode episode) {
            Glide.with(binding.imageEpisode)
                    .load(episode.getAir_date())
                    .into(binding.imageEpisode);
            binding.textEpisodeFullName.setText(episode.getName());

            itemView.setOnClickListener(v -> listener.onItemClickListener(episode.getId()));
        }
    }

    public void setOnItemClickListener (OnItemClickListener listener){
        this.listener = listener;}
    }

