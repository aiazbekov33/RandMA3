package com.example.randma3.ui.fragments.episode;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.randma3.databinding.FragmentEpisodeBinding;


public class EpisodeFragment extends Fragment {

    private FragmentEpisodeBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(getLayoutInflater(),container, false);
        return binding.getRoot();
           }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        listener();
    }

//    private void listener() {
//        binding.openDetailFragment.setOnClickListener(v -> {
//            Navigation.findNavController(v).navigate(EpisodeFragmentDirections.
//                    actionGlobalDetailFragment().setId(name));
//
//        });
//    }
}