package com.example.randma3.ui.fragments.location.detail;

import androidx.lifecycle.LiveData;
import com.example.randma3.base.BaseViewModel;
import com.example.randma3.data.local.daos.LocationDao;
import com.example.randma3.data.network.dtos.location.Location;
import com.example.randma3.data.repository.LocationRepository;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LocationDetailViewModel extends BaseViewModel {

    private final LocationRepository locationRepository;
    private final LocationDao locationDao;

    @Inject
    public LocationDetailViewModel(LocationRepository locationRepository, LocationDao locationDao){
        this.locationRepository = locationRepository;
        this.locationDao = locationDao;
    }
    public LiveData<Location> location;
    public LiveData<Location> isLoading;

    public LiveData<Location> fetchLocation(int id) {
        return locationRepository.fetchLocation(id);
    }

    public final LiveData<Boolean> loadingLocationDetail(){
        return locationRepository._isLoading;
    }
}
