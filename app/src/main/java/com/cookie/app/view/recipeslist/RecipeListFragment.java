package com.cookie.app.view.recipeslist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cookie.app.R;

import com.cookie.app.viewmodel.RecipeListViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeListFragment extends android.support.v4.app.Fragment {

    private RecipeListViewModel viewModel;
    @BindView(R.id.recipe_recycler_view) RecyclerView recipeRecyclerView;
    @BindView(R.id.recipe_progress_bar) ProgressBar progressBar;

    public RecipeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        ButterKnife.bind(this, view);

        recipeRecyclerView.setLayoutManager(new AutoGridLayoutManager(getContext(), getResources().getInteger(R.integer.recipe_card_width)));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(RecipeListViewModel.class);
        viewModel.init();
        viewModel.getRecipes().observe(this, recipes -> {
            progressBar.setVisibility(View.GONE);
            recipeRecyclerView.setAdapter(new RecipeListAdapter(getContext(), recipes));
        });
    }
}