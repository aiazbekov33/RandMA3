package com.example.randma3.ui.fragments.location;

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

import com.example.randma3.data.network.dtos.location.Location;
import com.example.randma3.databinding.FragmentLocationBinding;
import com.example.randma3.inter.OnItemClickListener;
import com.example.randma3.ui.adapters.location.LocationAdapter;

import java.time.LocalDate;


public class LocationFragment extends Fragment {
    
    private LocationViewModel viewModel;
    private FragmentLocationBinding binding;
    private LocationAdapter locationAdapter = new LocationAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setupListener();
        setupRequest();
        setupObserves();

    }

    private void initialize() {
        viewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        setupLocationRecycler();
    }

    private void setupLocationRecycler() {
        binding.recyclerLocation.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerLocation.setAdapter(locationAdapter);
    }

    private void setupListener() {

        locationAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int id) {
                Navigation.findNavController(requireView()).navigate(
                        LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(id).setId(id)
                );
            }

            @Override
            public void onItemLongClickListener(String image) {

            }
        });
    }

    private void setupRequest() {
        viewModel.fetchLocations();
    }

    private void setupObserves() {
        viewModel.location.observe(getViewLifecycleOwner(), locations -> {
                locationAdapter.submitList(locations.getResults());
    });

        viewModel.isLoading.observe(getViewLifecycleOwner(), isLoading ->{
        if (isLoading){
            binding.loaderLocation.setVisibility(View.VISIBLE);
            binding.recyclerLocation.setVisibility(View.GONE);
        } else {
            binding.loaderLocation.setVisibility(View.GONE);
            binding.recyclerLocation.setVisibility(View.VISIBLE);
        }
    });
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}