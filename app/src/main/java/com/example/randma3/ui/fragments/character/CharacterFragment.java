package com.example.randma3.ui.fragments.character;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.randma3.databinding.FragmentCharacterBinding;
import com.example.randma3.inter.OnItemClickListener;
import com.example.randma3.ui.adapters.characters.CharacterAdapter;


public class CharacterFragment extends Fragment {

    private CharacterViewModel viewModel;
    private FragmentCharacterBinding binding;
    private CharacterAdapter characterAdapter = new CharacterAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(getLayoutInflater(), container, false);
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
        viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        setupCharacterRecycler();

    }

    private void setupCharacterRecycler() {
        binding.recyclerCharacter.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerCharacter.setAdapter(characterAdapter);
    }

    private void setupListeners() {
        characterAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int id) {
                Navigation.findNavController(requireView()).navigate(
                        CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment().setId(id)
                );
            }

            @Override
            public void onItemLongClickListener(String image) {
                Navigation.findNavController(requireView()).navigate(
                        CharacterFragmentDirections.actionCharacterFragmentToMyDialogFragment(image)
                );
            }
        });
    }

    private void setupRequests() {
        viewModel.fetchCharacters();
    }

    private void setupObservers() {
        viewModel.character.observe(getViewLifecycleOwner(), characters -> {
            characterAdapter.submitList(characters.getResults());
        });
        viewModel.isLoading.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.loaderCharacter.setVisibility(View.VISIBLE);
                binding.recyclerCharacter.setVisibility(View.GONE);
            } else {
                binding.loaderCharacter.setVisibility(View.GONE);
                binding.recyclerCharacter.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}