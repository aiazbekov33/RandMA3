package com.example.randma3.ui.fragments.location;

import androidx.lifecycle.LiveData;
import com.example.randma3.base.BaseViewModel;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.location.Location;
import com.example.randma3.data.repository.LocationRepository;

public class LocationViewModel extends BaseViewModel {

    private final LocationRepository locationRepository = new LocationRepository();
    public int page = 1;

    public LiveData<RickAndMortyResponse<Location>> fetchLocations(){
        return locationRepository.fetchLocations(page);
    }
    public LiveData<Boolean> loadingLocations() {
        return locationRepository._isLoading;

    }
}
