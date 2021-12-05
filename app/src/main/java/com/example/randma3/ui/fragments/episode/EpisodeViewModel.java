package com.example.randma3.ui.fragments.episode;

import androidx.lifecycle.LiveData;
import com.example.randma3.base.BaseViewModel;
import com.example.randma3.data.local.daos.EpisodeDao;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.data.network.dtos.episode.Episode;
import com.example.randma3.data.repository.EpisodeRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EpisodeViewModel extends BaseViewModel {
    private final EpisodeRepository episodeRepository;
    public int page = 1;

    @Inject
    public EpisodeViewModel(EpisodeRepository episodeRepository){
        this.episodeRepository = episodeRepository;
    }

    public LiveData<RickAndMortyResponse<Episode>> fetchEpisodes(){
        return episodeRepository.fetchEpisodes(page);
    }
    public LiveData<Boolean> loadingEpisodes() {
        return episodeRepository._isLoading;
    }

    public List<Episode> getEpisodes() {
        return episodeRepository.getEpisodes();
    }
}
