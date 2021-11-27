package com.example.randma3.ui.adapters.characters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.databinding.ItemCharacterBinding;
import com.example.randma3.inter.OnItemClickListener;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private ArrayList<Character> list = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(
                ItemCharacterBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false
                ), listener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void submitList(ArrayList<Character> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {

        private final ItemCharacterBinding binding;
        private OnItemClickListener listener;

        public CharacterViewHolder(@NonNull ItemCharacterBinding binding, OnItemClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void onBind(Character character) {
            Glide.with(binding.imageCharacter)
                    .load(character.getImage())
                    .into(binding.imageCharacter);
            binding.textCharacterFullName.setText(character.getName());

            itemView.setOnClickListener(v -> {
                listener.onItemClickListener(character.getId());
            });
            itemView.setOnLongClickListener(v -> {
                listener.onItemLongClickListener(character.getImage());
                return false;
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
