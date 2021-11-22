package com.example.randma3.ui.fragments.episode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.randma3.App;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.episode.Episode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeViewModel extends ViewModel {
    private final MutableLiveData<RickAndMortyResponse<Episode>> _episode = new MutableLiveData();
    public final LiveData<RickAndMortyResponse<Episode>> episode = _episode;
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _isLoading;

    public void fetchEpisodes() {
        _isLoading.setValue(true);
        App.episodeApiServices.fetchEpisodes().enqueue(new Callback<RickAndMortyResponse<Episode>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Episode>> call, Response<RickAndMortyResponse<Episode>> response) {
                _episode.setValue(response.body());
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Episode>> call, Throwable t) {
                _episode.setValue(null);
                _isLoading.setValue(false);
            }
        });
    }
}
