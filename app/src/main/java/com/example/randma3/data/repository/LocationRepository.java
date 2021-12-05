package com.example.randma3.data.repository;

import androidx.lifecycle.MutableLiveData;
import com.example.randma3.App;
import com.example.randma3.data.local.daos.LocationDao;
import com.example.randma3.data.network.apiservices.LocationApiServices;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.location.Location;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {

    private final LocationApiServices locationApiServices;
    private final LocationDao locationDao;

    @Inject
    public LocationRepository(LocationApiServices locationApiServices, LocationDao locationDao){
        this.locationApiServices = locationApiServices;
        this.locationDao = locationDao;

    }
    public final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public MutableLiveData<RickAndMortyResponse<Location>> fetchLocations(int page){
        _isLoading.setValue(true);
        MutableLiveData<RickAndMortyResponse<Location>> data = new MutableLiveData<>();
        locationApiServices.fetchLocations(page).enqueue(new Callback<RickAndMortyResponse<Location>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Location>> call, Response<RickAndMortyResponse<Location>> response) {
                if (response.body() !=null){
                    locationDao.insert(response.body().getResults());
                    data.setValue(response.body());
                }
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Location>> call, Throwable t) {
                data.setValue(null);
                _isLoading.setValue(false);
            }
        });
        return data;
    }

    public MutableLiveData<Location> fetchLocation(int id){
        MutableLiveData<Location> _location = new MutableLiveData<>();
        _isLoading.setValue(true);
        locationApiServices.fetchLocation(id).enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                _location.setValue(response.body());
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                _location.setValue(null);
                _isLoading.setValue(true);
            }
        });
        return _location;
    }
    public List<Location> getLocations(){
        return locationDao.getAll();
    }

}
