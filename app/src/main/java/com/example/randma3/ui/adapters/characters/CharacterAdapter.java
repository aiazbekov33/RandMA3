package com.example.randma3.ui.adapters.characters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.databinding.ItemCharacterBinding;
import com.example.randma3.inter.OnItemClickListener;

public class CharacterAdapter extends ListAdapter<Character,CharacterAdapter.CharacterViewHolder> {

    public OnItemClickListener listener;

    public CharacterAdapter(){
        super(new CharacterDiffUtil());
    }

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
        holder.onBind(getItem(position));
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
                listener.onItemLongClickListener(getAdapterPosition(),character);
                return false;
            });
        }
    }

    private static class CharacterDiffUtil extends DiffUtil.ItemCallback<Character>{

        @Override
        public boolean areItemsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
            return oldItem == newItem;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
