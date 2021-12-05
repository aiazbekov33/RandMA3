package com.example.randma3.ui.fragments.episode;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.randma3.base.BaseFragment;
import com.example.randma3.data.network.dtos.RickAndMortyResponse;
import com.example.randma3.data.network.dtos.character.Character;
import com.example.randma3.data.network.dtos.episode.Episode;
import com.example.randma3.databinding.FragmentEpisodeBinding;
import com.example.randma3.inter.OnItemClickListener;
import com.example.randma3.ui.adapters.episode.EpisodeAdapter;
import com.example.randma3.ui.fragments.character.CharacterFragmentDirections;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class EpisodeFragment extends BaseFragment <EpisodeViewModel, FragmentEpisodeBinding> {

    private EpisodeAdapter adapter  = new EpisodeAdapter();
    private LinearLayoutManager layoutManager;
    private int totalItemCount, visibleItemCount, postVisibleItem;
    private ArrayList<Episode> episodes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(getLayoutInflater(),container, false);
        return binding.getRoot();
           }


    protected void initialize() {
        layoutManager = new LinearLayoutManager(getContext());
        viewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);
        binding.recyclerEpisode.setLayoutManager(layoutManager);
        binding.recyclerEpisode.setAdapter(adapter);
    }

    protected void setupListener() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(int id) {
                if (!isOnline()) {
                    Navigation.findNavController(requireView()).navigate(
                            CharacterFragmentDirections.actionCharacterFragmentToMyDialogFragment2("").setConnectionChecked(true)
                    );

                } else {
                }
                Navigation.findNavController(requireView()).navigate(
                        EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(id).setId(id)
                );
            }


            @Override
            public void onItemLongClickListener(int position, Character model) {

            }
        });
    }

     protected void setupObservers() {
         if (!isOnline()) {
             if (viewModel.getEpisodes().isEmpty()) {
                 Toast.makeText(getContext(), "НЕТ ДАННЫХ", Toast.LENGTH_SHORT).show();
             } else {
                 Toast.makeText(getContext(), "OFF-LINE", Toast.LENGTH_SHORT).show();
                 adapter.submitList(viewModel.getEpisodes());
             }

         } else {

             viewModel.loadingEpisodes().observe(getViewLifecycleOwner(), isLoading -> {
                 if (isLoading) {
                     binding.loaderEpisode.setVisibility(View.VISIBLE);
                     binding.recyclerEpisode.setVisibility(View.GONE);
                 } else {
                     binding.loaderEpisode.setVisibility(View.GONE);
                     binding.recyclerEpisode.setVisibility(View.VISIBLE);
                 }
             });
             viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), new Observer<RickAndMortyResponse<Episode>>() {
                 @Override
                 public void onChanged(RickAndMortyResponse<Episode> episode) {
                     if (episode != null) {
                         episodes.addAll(episode.getResults());
                         adapter.submitList(episodes);
                         String next = episode.getInfo().getNext();
                         if (next != null) {
                             binding.recyclerEpisode.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                 @Override
                                 public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                     super.onScrolled(recyclerView, dx, dy);
                                     if (dy > 0) {
                                         viewModel.loadingEpisodes().observe(getViewLifecycleOwner(), isLoading -> {
                                             if (isLoading) {
                                                 binding.loaderEpisode.setVisibility(View.GONE);
                                                 binding.recyclerEpisode.setVisibility(View.VISIBLE);
                                                 binding.episodeBar.setVisibility(View.VISIBLE);
                                             } else {
                                                 binding.episodeBar.setVisibility(View.GONE);
                                             }
                                         });
                                         visibleItemCount = layoutManager.getChildCount();
                                         totalItemCount = layoutManager.getItemCount();
                                         postVisibleItem = layoutManager.findFirstVisibleItemPosition();
                                         if ((visibleItemCount + postVisibleItem) >= totalItemCount) {
                                             viewModel.page++;
                                             viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), episodeModelRickAndMortyResponse -> {
                                                 if (episodeModelRickAndMortyResponse != null) {
                                                     episodes.addAll(episodeModelRickAndMortyResponse.getResults());
                                                     adapter.submitList(episodes);
                                                 }
                                             });
                                         }
                                     }
                                 }
                             });
                         }
                     }
                 }
             });
         }
     }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}