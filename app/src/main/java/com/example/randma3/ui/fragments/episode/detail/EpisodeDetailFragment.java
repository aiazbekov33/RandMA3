package com.example.randma3.ui.fragments.episode.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.episode.Episode;
import com.example.randma3.databinding.FragmentCharacterBinding;
import com.example.randma3.databinding.FragmentEpisodeBinding;
import com.example.randma3.databinding.FragmentEpisodeDetailBinding;
import com.example.randma3.ui.adapters.characters.CharacterAdapter;
import com.example.randma3.ui.adapters.episode.EpisodeAdapter;
import com.example.randma3.ui.fragments.character.CharacterFragmentDirections;
import com.example.randma3.ui.fragments.character.CharacterViewModel;
import com.example.randma3.ui.fragments.episode.EpisodeFragmentArgs;
import com.example.randma3.ui.fragments.episode.EpisodeViewModel;


public class EpisodeDetailFragment extends Fragment {

    private FragmentEpisodeDetailBinding binding;
    private EpisodeDetailViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeDetailBinding.inflate(getLayoutInflater(), container, false);
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
        viewModel = new ViewModelProvider(this).get(EpisodeDetailViewModel.class);
    }

    private void setupRequests() {
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(getArguments()).getId());
    }

    private void setupObservers() {
        viewModel.episode.observe(getViewLifecycleOwner(), new Observer<Episode>() {
            @Override
            public void onChanged(Episode episode) {
                Glide.with(binding.typeEpisodeDetail)
                        .load(episode.getType())
                        .into(binding.typeEpisodeDetail);
                binding.textEpisodeDetailId.setText(String.valueOf(episode.getId()));
                binding.textEpisodeDetailFullName.setText(String.valueOf(episode.getName()));
            }
        });
        viewModel.isLoading.observe(getViewLifecycleOwner(),isLoading ->{
            if (isLoading){
                binding.loaderEpisodeDetail.setVisibility(View.VISIBLE);
                binding.typeEpisodeDetail.setVisibility(View.GONE);
                binding.textEpisodeDetailId.setVisibility(View.GONE);
                binding.textEpisodeDetailFullName.setVisibility(View.GONE);
            } else {
                binding.loaderEpisodeDetail.setVisibility(View.GONE);
                binding.typeEpisodeDetail.setVisibility(View.VISIBLE);
                binding.textEpisodeDetailId.setVisibility(View.VISIBLE);
                binding.textEpisodeDetailFullName.setVisibility(View.VISIBLE);
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}