package com.example.randma3.ui.fragments.location.detail;

import androidx.lifecycle.LiveData;
import com.example.randma3.base.BaseViewModel;
import com.example.randma3.data.network.dtos.location.Location;
import com.example.randma3.data.repository.LocationRepository;

public class LocationDetailViewModel extends BaseViewModel {

    private final LocationRepository locationRepository = new LocationRepository();
    public LiveData<Location> location;
    public LiveData<Location> isLoading;


    public LiveData<Location> fetchLocation(int id) {
        return locationRepository.fetchLocation(id);
    }

    public final LiveData<Boolean> loadingLocationDetail(){
        return locationRepository._isLoading;
    }
}
