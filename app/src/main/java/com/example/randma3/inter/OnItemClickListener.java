package com.example.randma3.inter;

import com.example.randma3.data.network.dtos.character.Character;

public interface OnItemClickListener {
    void onItemClickListener(int id);
    void onItemLongClickListener(int position, Character model);
}
