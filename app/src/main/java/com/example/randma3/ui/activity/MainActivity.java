package com.example.randma3.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.randma3.R;
import com.example.randma3.databinding.ActivityMainBinding;
import com.example.randma3.ui.adapters.CharacterAdapter;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupNavigation();
    }
    private void setupNavigation() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.characterFragment, R.id.episodeFragment, R.id.locationFragment
        ).build();
        NavigationUI.setupWithNavController(binding.toolBar, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNav, navController);
    }

}