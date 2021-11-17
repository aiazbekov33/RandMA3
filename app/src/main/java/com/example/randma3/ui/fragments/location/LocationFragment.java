package com.example.randma3.ui.fragments.location;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.randma3.R;
import com.example.randma3.databinding.FragmentLocationBinding;


public class LocationFragment extends Fragment {

    private FragmentLocationBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();

    }
}