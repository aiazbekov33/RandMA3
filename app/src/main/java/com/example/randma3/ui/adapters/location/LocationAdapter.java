package com.example.randma3.ui.adapters.location;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.randma3.data.network.dtos.location.Location;
import com.example.randma3.databinding.ItemLocationBinding;
import com.example.randma3.inter.OnItemClickListener;

public class LocationAdapter extends ListAdapter<Location, LocationAdapter.LocationViewHolder>{

    public OnItemClickListener listener;

    public LocationAdapter() {
        super(new LocationDiffUtil());
    }

    @NonNull
    @Override
    public LocationAdapter.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationViewHolder(ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.LocationViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static class LocationDiffUtil extends DiffUtil.ItemCallback<Location> {
        @Override
        public boolean areItemsTheSame(@NonNull Location oldItem, @NonNull Location newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Location oldItem, @NonNull Location newItem) {
            return oldItem == newItem;
        }
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        private ItemLocationBinding binding;

        public LocationViewHolder(ItemLocationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Location location) {
            Glide.with(binding.imageLocation)
                    .load(location.getType())
                    .into(binding.imageLocation);
            binding.textLocationFullName.setText(location.getName());

            itemView.setOnClickListener(v -> listener.onItemClickListener(location.getId()));
        }
    }

    public void setOnItemClickListener (OnItemClickListener listener){
        this.listener = listener;}
}

