package com.example.randma3.data.repository;

import androidx.lifecycle.MutableLiveData;
import com.example.randma3.App;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.episode.Episode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepository {

    public final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public MutableLiveData<RickAndMortyResponse<Episode>> fetchEpisodes(int page){
        _isLoading.setValue(true);
        MutableLiveData<RickAndMortyResponse<Episode>> data = new MutableLiveData<>();
        App.episodeApiServices.fetchEpisodes(page).enqueue(new Callback<RickAndMortyResponse<Episode>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Episode>> call, Response<RickAndMortyResponse<Episode>> response) {
                data.setValue(response.body());
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Episode>> call, Throwable t) {
                data.setValue(null);
                _isLoading.setValue(false);
            }
        });
        return data;
    }

    public MutableLiveData<Episode> fetchEpisode(int id){
        MutableLiveData<Episode> _episode = new MutableLiveData<>();
        _isLoading.setValue(true);
        App.episodeApiServices.fetchEpisode(id).enqueue(new Callback<Episode>() {
            @Override
            public void onResponse(Call<Episode> call, Response<Episode> response) {
                _episode.setValue(response.body());
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Episode> call, Throwable t) {
                _episode.setValue(null);
                _isLoading.setValue(true);
            }
        });
        return _episode;
    }

}
