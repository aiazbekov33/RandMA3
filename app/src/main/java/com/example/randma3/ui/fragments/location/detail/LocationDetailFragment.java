package com.example.randma3.ui.fragments.location.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.randma3.R;
import com.example.randma3.data.network.dtos.episode.Episode;
import com.example.randma3.data.network.dtos.location.Location;
import com.example.randma3.databinding.FragmentEpisodeDetailBinding;
import com.example.randma3.databinding.FragmentLocationBinding;
import com.example.randma3.databinding.FragmentLocationDetailBinding;
import com.example.randma3.ui.fragments.episode.detail.EpisodeDetailFragmentArgs;
import com.example.randma3.ui.fragments.episode.detail.EpisodeDetailViewModel;


public class LocationDetailFragment extends Fragment {

    private FragmentLocationDetailBinding binding;
    private LocationDetailViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationDetailBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setupRequests();
        setupObservers();

    }

    private void initialize() {
        viewModel = new ViewModelProvider(this).get(LocationDetailViewModel.class);
    }

    private void setupRequests() {
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(getArguments()).getId());
    }

    private void setupObservers() {
        viewModel.location.observe(getViewLifecycleOwner(), new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                Glide.with(binding.typeLocationDetail)
                        .load(location.getType())
                        .into(binding.typeLocationDetail);
                binding.textLocationDetailId.setText(String.valueOf(location.getId()));
                binding.textLocationDetailFullName.setText(String.valueOf(location.getName()));
            }
        });
        viewModel.isLoading.observe(getViewLifecycleOwner(),isLoading ->{
            if (isLoading){
                binding.loaderLocationDetail.setVisibility(View.VISIBLE);
                binding.typeLocationDetail.setVisibility(View.GONE);
                binding.textLocationDetailId.setVisibility(View.GONE);
                binding.textLocationDetailFullName.setVisibility(View.GONE);
            } else {
                binding.loaderLocationDetail.setVisibility(View.GONE);
                binding.typeLocationDetail.setVisibility(View.VISIBLE);
                binding.textLocationDetailId.setVisibility(View.VISIBLE);
                binding.textLocationDetailFullName.setVisibility(View.VISIBLE);
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}