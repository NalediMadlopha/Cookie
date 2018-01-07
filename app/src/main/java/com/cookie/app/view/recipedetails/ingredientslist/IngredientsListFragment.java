package com.cookie.app.view.recipedetails.ingredientslist;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cookie.app.R;
import com.cookie.app.Utils.Util;
import com.cookie.app.viewmodel.RecipeDetailsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsListFragment extends Fragment {

    private RecipeDetailsViewModel viewModel;
    @BindView(R.id.ingredients_recycler_view) RecyclerView ingredientsRecyclerView;

    public IngredientsListFragment() {
        // Required empty public constructor
    }

    public static IngredientsListFragment getInstance() {
        return new IngredientsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this, view);

        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(RecipeDetailsViewModel.class);
        viewModel.getRecipe().observe(this, recipe -> {
            ingredientsRecyclerView.setAdapter(new IngredientsListAdapter(getActivity(), recipe.getIngredients()));
            // TODO: 1/8/18 Remove this line and implement a settings option
            Util.saveAppWidgetPreferences(getActivity(), recipe);
        });

    }
}
