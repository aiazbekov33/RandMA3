package com.example.randma3.ui.fragments.location.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.randma3.App;
import com.example.randma3.data.network.dtos.episode.Episode;
import com.example.randma3.data.network.dtos.location.Location;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationDetailViewModel extends ViewModel {

    private final MutableLiveData<Location> _location = new MutableLiveData();
    public final LiveData<Location> location = _location;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _isLoading;

    public void fetchLocation (int id){
        _isLoading.setValue(true);

        App.locationApiServices.fetchLocation(id).enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                _location.setValue(response.body());
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                _location.setValue(null);
                _isLoading.setValue(false);
            }
        });
    }
}
