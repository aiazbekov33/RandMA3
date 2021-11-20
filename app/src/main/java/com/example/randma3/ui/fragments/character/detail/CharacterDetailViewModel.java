package com.example.randma3.ui.fragments.character.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.randma3.App;
import com.example.randma3.data.network.dtos.character.Character;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterDetailViewModel extends ViewModel {

    private final MutableLiveData<Character> _character = new MutableLiveData();
    public final LiveData<Character> character = _character;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public final LiveData<Boolean> isLoading = _isLoading;


    public void fetchCharacter(int id) {

        _isLoading.setValue(true);

        App.characterApiServices.fetchCharacter(id).enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                _character.setValue(response.body());
                _isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                _character.setValue(null);
                _isLoading.setValue(false);
            }
        });
    }
}
