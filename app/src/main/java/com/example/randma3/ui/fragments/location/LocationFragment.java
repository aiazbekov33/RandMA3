package com.example.randma3.ui.fragments.location;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.randma3.base.BaseFragment;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.data.network.dtos.location.Location;
import com.example.randma3.databinding.FragmentLocationBinding;
import com.example.randma3.inter.OnItemClickListener;
import com.example.randma3.ui.adapters.location.LocationAdapter;
import com.example.randma3.ui.fragments.character.CharacterFragmentDirections;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LocationFragment extends BaseFragment<LocationViewModel, FragmentLocationBinding> {

    LocationAdapter adapter = new LocationAdapter();

    private LinearLayoutManager layoutManager;
    private int totalItemCount, visibleItemCount, postVisibleItem;
    private ArrayList<Location> locations = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(getLayoutInflater(),container, false);
        return binding.getRoot();
    }


    protected void initialize() {
        layoutManager = new LinearLayoutManager(getContext());
        viewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        binding.recyclerLocation.setLayoutManager(layoutManager);
        binding.recyclerLocation.setAdapter(adapter);
    }

    protected void setupListener() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int id) {
                if (!isOnline()) {
                    Navigation.findNavController(requireView()).navigate(
                            CharacterFragmentDirections.actionCharacterFragmentToMyDialogFragment2("").setConnectionChecked(true)
                    );
                } else {
                    Navigation.findNavController(requireView()).navigate(
                            LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(id).setId(id)
                    );
                }
            }

            @Override
            public void onItemLongClickListener(int position, Character model) {
            }
        });
    }

    protected void setupObservers() {
        if (!isOnline()) {
            if (viewModel.getLocation().isEmpty()) {
                Toast.makeText(getContext(), "НЕТ ДАННЫХ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "OFF-LINE", Toast.LENGTH_SHORT).show();
                adapter.submitList(viewModel.getLocation());
            }

        } else {
            viewModel.fetchLocations().observe(getViewLifecycleOwner(), characterRickAndMortyResponse -> {
                if (characterRickAndMortyResponse != null) {
                    locations.addAll(characterRickAndMortyResponse.getResults());
                    adapter.submitList(locations);
                }
            });
        viewModel.loadingLocations().observe(getViewLifecycleOwner(), isLoading ->{
            if (isLoading){
                binding.loaderLocation.setVisibility(View.VISIBLE);
                binding.recyclerLocation.setVisibility(View.GONE);
            } else {
                binding.loaderLocation.setVisibility(View.GONE);
                binding.recyclerLocation.setVisibility(View.VISIBLE);
            }
        });
        viewModel.fetchLocations().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<Location>>() {
            @Override
            public void onChanged(RickAndMortyResponse<Location> location) {
                if (location != null){
                    locations.addAll(location.getResults());
                    adapter.submitList(locations);
                    String next = location.getInfo().getNext();
                    if (next != null) {
                        binding.recyclerLocation.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);
                                if (dy > 0) {
                                    viewModel.loadingLocations().observe(getViewLifecycleOwner(), isLoading -> {
                                        if (isLoading) {
                                            binding.loaderLocation.setVisibility(View.GONE);
                                            binding.recyclerLocation.setVisibility(View.VISIBLE);
                                            binding.locationBar.setVisibility(View.VISIBLE);
                                        } else {
                                            binding.locationBar.setVisibility(View.GONE);
                                        }
                                    });
                                    visibleItemCount = layoutManager.getChildCount();
                                    totalItemCount = layoutManager.getItemCount();
                                    postVisibleItem = layoutManager.findFirstVisibleItemPosition();
                                    if ((visibleItemCount + postVisibleItem) >= totalItemCount) {
                                        viewModel.page++;
                                        viewModel.fetchLocations().observe(getViewLifecycleOwner(), episodeModelRickAndMortyResponse -> {
                                            if (episodeModelRickAndMortyResponse != null) {
                                                locations.addAll(episodeModelRickAndMortyResponse.getResults());
                                                adapter.submitList(locations);
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
    }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}