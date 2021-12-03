package com.example.randma3.ui.fragments.character.detail;

import androidx.lifecycle.LiveData;
import com.example.randma3.base.BaseViewModel;
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.data.repository.CharacterRepository;

public class CharacterDetailViewModel extends BaseViewModel {

    private final CharacterRepository characterRepository = new CharacterRepository();
    public LiveData<Character> character;
    public LiveData<Character> isLoading;


    public LiveData<Character> fetchCharacter(int id) {
        return characterRepository.fetchCharacter(id);
    }

    public final LiveData<Boolean> loadingCharacterDetail(){
        return characterRepository._isLoading;
    }



}
