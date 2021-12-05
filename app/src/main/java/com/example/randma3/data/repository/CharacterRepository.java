package com.example.randma3.data.repository;

import androidx.lifecycle.MutableLiveData;
import com.example.randma3.App;
import com.example.randma3.data.local.daos.CharacterDao;
import com.example.randma3.data.network.apiservices.CharacterApiServices;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.character.Character;

import java.util.List;

import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {

    private final CharacterApiServices characterApiServices;
    private final CharacterDao characterDao;


    @Inject
    public CharacterRepository(CharacterApiServices characterApiServices, CharacterDao characterDao){
        this.characterApiServices = characterApiServices;
        this.characterDao = characterDao;
    }

    public final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public final MutableLiveData<RickAndMortyResponse<Character>> fetchCharacters(int page){
        _isLoading.setValue(true);
        MutableLiveData<RickAndMortyResponse<Character>> data = new MutableLiveData<>();
        characterApiServices.fetchCharacters(page).enqueue(new Callback<RickAndMortyResponse<Character>>() {
            @Override
            public void onResponse(Call<RickAndMortyResponse<Character>> call, Response<RickAndMortyResponse<Character>> response) {
                if (response.body() != null) {
                    characterDao.insert(response.body().getResults());
                    data.setValue(response.body());
                }
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<RickAndMortyResponse<Character>> call, Throwable t) {
                data.setValue(null);
                _isLoading.setValue(true);
            }
        });
        return data;
    }

    public MutableLiveData<Character> fetchCharacter (int id){
        MutableLiveData<Character> _character = new MutableLiveData<>();
        _isLoading.setValue(true);
        characterApiServices.fetchCharacter(id).enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                _character.setValue(response.body());
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                _character.setValue(null);
                _isLoading.setValue(true);

            }
        });
        return _character;
    }
    public List<Character> getCharacters(){
        return characterDao.getAll();
    }
}
