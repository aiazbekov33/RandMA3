package com.example.randma3.ui.fragments.character;

import androidx.lifecycle.LiveData;

import com.example.randma3.base.BaseViewModel;
import com.example.randma3.data.local.daos.CharacterDao;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.data.repository.CharacterRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CharacterViewModel extends BaseViewModel {

    private final CharacterRepository characterRepository;
    public int page = 1;


    @Inject
    public CharacterViewModel(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public LiveData<RickAndMortyResponse<Character>> fetchCharacters() {
        return characterRepository.fetchCharacters(page);
    }

    public LiveData<Boolean> loadingCharacter() {
        return characterRepository._isLoading;

    }

    public List<Character> getCharacters() {
        return characterRepository.getCharacters();
    }
}
