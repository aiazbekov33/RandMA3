package com.example.randma3.ui.fragments.episode.detail;

import androidx.lifecycle.LiveData;
import com.example.randma3.base.BaseViewModel;
import com.example.randma3.data.network.dtos.episode.Episode;
import com.example.randma3.data.repository.EpisodeRepository;

public class EpisodeDetailViewModel extends BaseViewModel {

    private final EpisodeRepository episodeRepository = new EpisodeRepository();
    public LiveData<Episode> episode;
    public LiveData<Episode> isLoading;


    public LiveData<Episode> fetchEpisode(int id) {
        return episodeRepository.fetchEpisode(id);
    }

    public final LiveData<Boolean> loadingEpisodeDetail(){
        return episodeRepository._isLoading;
    }
}
