package com.example.randma3.ui.fragments.character.detail;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.randma3.R;
import com.example.randma3.base.BaseFragment;
import com.example.randma3.databinding.FragmentCharacterDetailBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CharacterDetailFragment extends BaseFragment<CharacterDetailViewModel, FragmentCharacterDetailBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterDetailBinding.inflate(getLayoutInflater(),container,false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(CharacterDetailViewModel.class);
    }

    @Override
    protected void setupObservers() {
        viewModel.fetchCharacter(CharacterDetailFragmentArgs.fromBundle(getArguments()).getId())
        .observe(getViewLifecycleOwner(), character -> {
            Glide.with(binding.imageCharacterDetail)
                    .load(character.getImage())
                    .into(binding.imageCharacterDetail);
            binding.textCharacterDetailId.setText(String.valueOf(character.getId()));
            binding.textCharacterDetailFullName.setText(String.valueOf(character.getName()));
            binding.textCharacterDetailSpecies.setText(String.valueOf(character.getSpecies()));
            binding.textCharacterDetailGender.setText(String.valueOf(character.getGender()));
            binding.textCharacterDetailType.setText(String.valueOf(character.getType()));
            binding.textCharacterDetailStatus.setText(String.valueOf(character.getStatus()));
            if (character.getStatus() !=null){
                switch (character.getStatus()){
                    case "Alive":
                        binding.imStatus.setBackgroundColor(R.drawable.for_status_alive);
                        break;
                    case "Dead":
                        binding.imStatus.setBackgroundColor(R.drawable.for_status_dead);
                        break;
                }
            }
        });
        viewModel.loadingCharacterDetail().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.loaderCharacterDetail.setVisibility(View.VISIBLE);
                binding.imageCharacterDetail.setVisibility(View.GONE);
                binding.textCharacterDetailId.setVisibility(View.GONE);
                binding.textCharacterDetailFullName.setVisibility(View.GONE);
                binding.textCharacterDetailSpecies.setVisibility(View.GONE);
                binding.textCharacterDetailGender.setVisibility(View.GONE);
                binding.textCharacterDetailType.setVisibility(View.GONE);
                binding.textCharacterDetailStatus.setVisibility(View.GONE);
            } else {
                binding.loaderCharacterDetail.setVisibility(View.GONE);
                binding.imageCharacterDetail.setVisibility(View.VISIBLE);
                binding.textCharacterDetailId.setVisibility(View.VISIBLE);
                binding.textCharacterDetailFullName.setVisibility(View.VISIBLE);
                binding.textCharacterDetailSpecies.setVisibility(View.VISIBLE);
                binding.textCharacterDetailGender.setVisibility(View.VISIBLE);
                binding.textCharacterDetailType.setVisibility(View.VISIBLE);
                binding.textCharacterDetailStatus.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}