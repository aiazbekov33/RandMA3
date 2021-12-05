package com.example.randma3.ui.fragments.episode.detail;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.randma3.base.BaseFragment;
import com.example.randma3.databinding.FragmentEpisodeDetailBinding;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EpisodeDetailFragment extends BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeDetailBinding.inflate(getLayoutInflater(),container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(EpisodeDetailViewModel.class);
    }

    protected void setupObservers() {
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(getArguments()).getId())
                .observe(getViewLifecycleOwner(), episode -> {
                    binding.textEpisodeDetailId.setText(String.valueOf(episode.getId()));
                    binding.textEpisodeDetailFullName.setText(String.valueOf(episode.getName()));
                    binding.textEpisodeDetailAirDate.setText(String.valueOf(episode.getAir_date()));
                    binding.textEpisodeDetailEpisode.setText(String.valueOf(episode.getEpisode()));
                    binding.textEpisodeDetailCreated.setText(String.valueOf(episode.getCreated()));
                    binding.textEpisodeDetailUrl.setText(String.valueOf(episode.getUrl()));
                });

        viewModel.loadingEpisodeDetail().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.loaderEpisodeDetail.setVisibility(View.VISIBLE);
                binding.textEpisodeDetailId.setVisibility(View.GONE);
                binding.textEpisodeDetailFullName.setVisibility(View.GONE);
                binding.textEpisodeDetailAirDate.setVisibility(View.GONE);
                binding.textEpisodeDetailEpisode.setVisibility(View.GONE);
                binding.textEpisodeDetailCreated.setVisibility(View.GONE);
                binding.textEpisodeDetailUrl.setVisibility(View.GONE);
            } else {
                binding.loaderEpisodeDetail.setVisibility(View.GONE);
                binding.textEpisodeDetailId.setVisibility(View.VISIBLE);
                binding.textEpisodeDetailFullName.setVisibility(View.VISIBLE);
                binding.textEpisodeDetailAirDate.setVisibility(View.VISIBLE);
                binding.textEpisodeDetailEpisode.setVisibility(View.VISIBLE);
                binding.textEpisodeDetailCreated.setVisibility(View.VISIBLE);
                binding.textEpisodeDetailUrl.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}