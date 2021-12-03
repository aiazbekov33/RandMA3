package com.example.randma3.ui.fragments.episode;

import androidx.lifecycle.LiveData;
import com.example.randma3.base.BaseViewModel;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.episode.Episode;
import com.example.randma3.data.repository.EpisodeRepository;

public class EpisodeViewModel extends BaseViewModel {
    private final EpisodeRepository episodeRepository = new EpisodeRepository();
    public int page = 1;

    public LiveData<RickAndMortyResponse<Episode>> fetchEpisodes(){
        return episodeRepository.fetchEpisodes(page);
    }
    public LiveData<Boolean> loadingEpisodes() {
        return episodeRepository._isLoading;
    }
}
