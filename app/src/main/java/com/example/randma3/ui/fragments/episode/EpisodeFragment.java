package com.example.randma3.ui.fragments.episode;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.randma3.databinding.FragmentEpisodeBinding;
import com.example.randma3.ui.adapters.episode.EpisodeAdapter;


public class EpisodeFragment extends Fragment {

    private EpisodeViewModel viewModel;
    private FragmentEpisodeBinding binding;
    private EpisodeAdapter episodeAdapter = new EpisodeAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(getLayoutInflater(),container, false);
        return binding.getRoot();
           }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setupListeners();
        setupRequests();
        setupObservers();

    }

    private void initialize() {
        viewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);
        setupEpisodeRecycler();
    }

    private void setupEpisodeRecycler() {
        binding.recyclerEpisode.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerEpisode.setAdapter(episodeAdapter);
    }

    private void setupListeners() {
        episodeAdapter.setOnItemClickListener(id -> {
            Navigation.findNavController(requireView()).navigate(
                    EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(id).setId(id)
            );
        });
    }

    private void setupRequests() {
        viewModel.fetchEpisodes();
    }

    private void setupObservers() {
        viewModel.episode.observe(getViewLifecycleOwner(), episodes -> {
            episodeAdapter.submitList(episodes.getResults());
        });
        viewModel.isLoading.observe(getViewLifecycleOwner(), isLoading ->{
            if (isLoading){
                binding.loaderEpisode.setVisibility(View.VISIBLE);
                binding.recyclerEpisode.setVisibility(View.GONE);
            } else {
                binding.loaderEpisode.setVisibility(View.GONE);
                binding.recyclerEpisode.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}