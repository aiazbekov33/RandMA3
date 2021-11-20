package com.example.randma3.ui.fragments.character.detail;

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
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.databinding.FragmentCharacterDetailBinding;

public class CharacterDetailFragment extends Fragment {


    private FragmentCharacterDetailBinding binding;
    private CharacterDetailViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterDetailBinding.inflate(getLayoutInflater(),container, false);
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
        viewModel = new ViewModelProvider(this).get(CharacterDetailViewModel.class);
    }

    private void setupRequests() {
        viewModel.fetchCharacter(CharacterDetailFragmentArgs.fromBundle(getArguments()).getId());
    }

    private void setupObservers() {
        viewModel.character.observe(getViewLifecycleOwner(), new Observer<Character>() {
            @Override
            public void onChanged(Character character) {
                Glide.with(binding.imageCharacterDetail)
                        .load(character.getImage())
                        .into(binding.imageCharacterDetail);
                binding.textCharacterDetailId.setText(String.valueOf(character.getId()));
                binding.textCharacterDetailFullName.setText(String.valueOf(character.getName()));
            }
        });
        viewModel.isLoading.observe(getViewLifecycleOwner(),isLoading ->{
            if (isLoading){
                binding.loaderCharacterDetail.setVisibility(View.VISIBLE);
                binding.imageCharacterDetail.setVisibility(View.GONE);
                binding.textCharacterDetailId.setVisibility(View.GONE);
                binding.textCharacterDetailFullName.setVisibility(View.GONE);
            } else {
                binding.loaderCharacterDetail.setVisibility(View.GONE);
                binding.imageCharacterDetail.setVisibility(View.VISIBLE);
                binding.textCharacterDetailId.setVisibility(View.VISIBLE);
                binding.textCharacterDetailFullName.setVisibility(View.VISIBLE);

            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}