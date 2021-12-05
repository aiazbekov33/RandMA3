package com.example.randma3.ui.fragments.location;

import androidx.lifecycle.LiveData;
import com.example.randma3.base.BaseViewModel;
import com.example.randma3.data.local.daos.LocationDao;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.data.network.dtos.location.Location;
import com.example.randma3.data.repository.LocationRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LocationViewModel extends BaseViewModel {

    private final LocationRepository locationRepository;
    public int page = 1;

    @Inject
    public LocationViewModel(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    public LiveData<RickAndMortyResponse<Location>> fetchLocations(){
        return locationRepository.fetchLocations(page);
    }
    public LiveData<Boolean> loadingLocations() {
        return locationRepository._isLoading;
    }
    public List<Location> getLocation() {
        return locationRepository.getLocations();
    }
}
