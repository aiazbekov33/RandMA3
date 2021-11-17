package com.example.randma3.ui.fragments.epizode;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.randma3.R;
import com.example.randma3.databinding.FragmentEpisodeBinding;



public class EpisodeFragment extends Fragment {

    private FragmentEpisodeBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(getLayoutInflater(),container, false);
        return binding.getRoot();

    }
}