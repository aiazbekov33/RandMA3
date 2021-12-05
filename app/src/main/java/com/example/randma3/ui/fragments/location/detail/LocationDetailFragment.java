package com.example.randma3.ui.fragments.location.detail;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.randma3.base.BaseFragment;
import com.example.randma3.databinding.FragmentLocationDetailBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LocationDetailFragment extends BaseFragment<LocationDetailViewModel, FragmentLocationDetailBinding> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationDetailBinding.inflate(getLayoutInflater(),container, false);
        return binding.getRoot();
    }

    protected void initialize() {
        viewModel = new ViewModelProvider(this).get(LocationDetailViewModel.class);
    }

    protected void setupObservers() {
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(getArguments()).getId())
                .observe(getViewLifecycleOwner(), location -> {
                    binding.textLocationDetailId.setText(String.valueOf(location.getId()));
                    binding.textLocationDetailFullName.setText(String.valueOf(location.getName()));
                    binding.textLocationDetailCreated.setText(String.valueOf(location.getCreated()));
                    binding.textLocationDetailDimension.setText(String.valueOf(location.getDimension()));
                    binding.textLocationDetailType.setText(String.valueOf(location.getType()));
                    binding.textLocationDetailUrl.setText(String.valueOf(location.getUrl()));
                });

        viewModel.loadingLocationDetail().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                binding.loaderLocationDetail.setVisibility(View.VISIBLE);
                binding.textLocationDetailId.setVisibility(View.GONE);
                binding.textLocationDetailFullName.setVisibility(View.GONE);
                binding.textLocationDetailCreated.setVisibility(View.GONE);
                binding.textLocationDetailDimension.setVisibility(View.GONE);
                binding.textLocationDetailType.setVisibility(View.GONE);
                binding.textLocationDetailUrl.setVisibility(View.GONE);
            } else {
                binding.loaderLocationDetail.setVisibility(View.GONE);
                binding.textLocationDetailId.setVisibility(View.VISIBLE);
                binding.textLocationDetailFullName.setVisibility(View.VISIBLE);
                binding.textLocationDetailCreated.setVisibility(View.VISIBLE);
                binding.textLocationDetailDimension.setVisibility(View.VISIBLE);
                binding.textLocationDetailType.setVisibility(View.VISIBLE);
                binding.textLocationDetailUrl.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}