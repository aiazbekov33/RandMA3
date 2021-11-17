package com.example.randma3.ui.fragments.location;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.randma3.R;
import com.example.randma3.databinding.FragmentLocationBinding;
import com.example.randma3.ui.fragments.character.CharacterFragmentDirections;


public class LocationFragment extends Fragment {

    private FragmentLocationBinding binding;
    String name = "I'll be back";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listener();
    }

    private void listener() {
        binding.openDetailFragment.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(LocationFragmentDirections.
                    actionGlobalDetailFragment().setId(name));

        });
    }
}