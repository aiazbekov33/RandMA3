package com.example.randma3.ui.fragments.character;

import androidx.lifecycle.LiveData;
import com.example.randma3.base.BaseViewModel;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.data.repository.CharacterRepository;

public class CharacterViewModel extends BaseViewModel {

    private final CharacterRepository characterRepository = new CharacterRepository();
    public int page = 1;

    public LiveData<RickAndMortyResponse<Character>> fetchCharacters(){
        return characterRepository.fetchCharacters(page);
    }
    public LiveData<Boolean> loadingCharacter() {
        return characterRepository._isLoading;

    }
}
