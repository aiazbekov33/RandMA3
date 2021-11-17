package com.example.randma3.ui.fragments.character;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.randma3.R;
import com.example.randma3.databinding.FragmentCharacterBinding;


public class CharacterFragment extends Fragment {

    private FragmentCharacterBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(getLayoutInflater(),container, false);
        return binding.getRoot();
    }
}