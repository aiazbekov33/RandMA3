package com.example.randma3.ui.fragments.episode.detail;

import androidx.lifecycle.LiveData;
import com.example.randma3.base.BaseViewModel;
import com.example.randma3.data.local.daos.CharacterDao;
import com.example.randma3.data.local.daos.EpisodeDao;
import com.example.randma3.data.network.dtos.episode.Episode;
import com.example.randma3.data.repository.CharacterRepository;
import com.example.randma3.data.repository.EpisodeRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
@HiltViewModel
public class EpisodeDetailViewModel extends BaseViewModel {

    private final EpisodeRepository episodeRepository;

    @Inject
    public EpisodeDetailViewModel(EpisodeRepository episodeRepository, EpisodeDao episodeDao){
        this.episodeRepository = episodeRepository;
    }
    public LiveData<Episode> episode;

    public LiveData<Episode> fetchEpisode(int id) {
        return episodeRepository.fetchEpisode(id);
    }

    public final LiveData<Boolean> loadingEpisodeDetail(){
        return episodeRepository._isLoading;
    }
}
