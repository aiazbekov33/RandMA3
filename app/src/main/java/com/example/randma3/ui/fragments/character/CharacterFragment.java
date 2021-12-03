package com.example.randma3.ui.fragments.character;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.randma3.base.BaseFragment;
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.databinding.FragmentCharacterBinding;
import com.example.randma3.inter.OnItemClickListener;
import com.example.randma3.ui.adapters.characters.CharacterAdapter;
import java.util.ArrayList;


public class CharacterFragment extends BaseFragment<CharacterViewModel, FragmentCharacterBinding> {

    CharacterAdapter adapter = new CharacterAdapter();
    private LinearLayoutManager layoutManager;
    private int totalItemCount, visibleItemCount, pastVisibleItem;
    private ArrayList<Character> characters = new ArrayList<>();
    private boolean progressLoading = true;
    private boolean isProgressLoading = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    protected void initialize() {
        layoutManager = new LinearLayoutManager(getContext());
        viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        binding.recyclerCharacter.setLayoutManager(layoutManager);
        binding.recyclerCharacter.setAdapter(adapter);
    }

    @Override
    protected void setupListener() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int id) {
                Navigation.findNavController(requireView()).navigate(
                        CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment().setId(id)
                );
            }

            @Override
            public void onItemLongClickListener(int position, Character character) {
                Navigation.findNavController(CharacterFragment.this.requireView()).navigate(
                        CharacterFragmentDirections.actionCharacterFragmentToMyDialogFragment(character.getImage())
                );
            }

        });
    }

    protected void setupObservers() {
        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), characterRickAndMortyResponse -> {
            if (characterRickAndMortyResponse != null) {
                characters.addAll(characterRickAndMortyResponse.getResults());
                adapter.submitList(characters);
            }
        });
        viewModel.loadingCharacter().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.loaderCharacter.setVisibility(View.VISIBLE);
                binding.recyclerCharacter.setVisibility(View.GONE);
            } else {
                binding.loaderCharacter.setVisibility(View.GONE);
                binding.recyclerCharacter.setVisibility(View.VISIBLE);
            }
        });

        binding.recyclerCharacter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    viewModel.loadingCharacter().observe(getViewLifecycleOwner(), isLoading -> {
                        if (isLoading) {
                            binding.loaderCharacter.setVisibility(View.GONE);
                            binding.recyclerCharacter.setVisibility(View.VISIBLE);
                        }
                    });
                    visibleItemCount = layoutManager.getItemCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition();
                    if (progressLoading) {
                        if ((visibleItemCount + pastVisibleItem) >= totalItemCount) {
                            progressLoading = false;
                            viewModel.page++;
                            viewModel.fetchCharacters().observe(getViewLifecycleOwner(), characterRickAndMortyResponse -> {
                                if (characterRickAndMortyResponse != null) {
                                    binding.characterBar.setVisibility(View.INVISIBLE);
                                    characters.addAll(characterRickAndMortyResponse.getResults());
                                    adapter.submitList(characters);
                                } else {
                                    isProgressLoading = false;
                                    binding.recyclerCharacter.setPadding(0, 0, 0, 0);
                                    binding.characterBar.setVisibility(View.GONE);
                                }
                            });
                            if (isProgressLoading) {
                                binding.characterBar.setVisibility(View.VISIBLE);
                            }
                            progressLoading = true;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}