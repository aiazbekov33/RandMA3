package com.example.randma3.ui.fragments.location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.randma3.App;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.location.Location;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationViewModel extends ViewModel {

    private final MutableLiveData<RickAndMortyResponse<Location>> _location = new MutableLiveData();
    public final LiveData<RickAndMortyResponse<Location>> location = _location;
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _isLoading;

    public void fetchLocations() {
        _isLoading.setValue(true);
        App.locationApiServices.fetchLocations().enqueue(new Callback<RickAndMortyResponse<Location>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Location>> call, Response<RickAndMortyResponse<Location>> response) {
                _location.setValue(response.body());
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Location>> call, Throwable t) {
                _location.setValue(null);
                _isLoading.setValue(false);
            }
        });
    }
}
