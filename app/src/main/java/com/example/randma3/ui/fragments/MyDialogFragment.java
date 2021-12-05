package com.example.randma3.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.randma3.databinding.FragmentDialogBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MyDialogFragment extends DialogFragment {

    private FragmentDialogBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDialogBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String image = MyDialogFragmentArgs.fromBundle(getArguments()).getImage();

        boolean connection_checked = MyDialogFragmentArgs.fromBundle(getArguments()).getConnectionChecked();
        if (connection_checked) {
            binding.dialogIv.setVisibility(View.GONE);
            binding.noInternetConnection.setVisibility(View.VISIBLE);
        } else {
            binding.noInternetConnection.setVisibility(View.GONE);
            binding.dialogIv.setVisibility(View.VISIBLE);
            Glide.with(binding.dialogIv)
                    .load(image)
                    .into(binding.dialogIv);
        }

    }


}