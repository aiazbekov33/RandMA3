package com.example.randma3.ui.fragments.episode.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.randma3.App;
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.data.network.dtos.episode.Episode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeDetailViewModel extends ViewModel {

    private final MutableLiveData<Episode> _episode = new MutableLiveData();
    public final LiveData<Episode> episode = _episode;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _isLoading;

    public void fetchEpisode (int id){
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
                _isLoading.setValue(false);

            }
        });
    }
}
