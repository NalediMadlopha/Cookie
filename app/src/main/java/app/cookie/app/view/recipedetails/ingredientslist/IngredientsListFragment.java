package app.cookie.app.view.recipedetails.ingredientslist;


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

import app.cookie.app.viewmodel.RecipeDetailsViewModel;

public class IngredientsListFragment extends Fragment {

    private RecipeDetailsViewModel viewModel;
    private RecyclerView ingredientsRecyclerView;

    public IngredientsListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ingredientsRecyclerView = rootView.findViewById(R.id.ingredients_recycler_view);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(RecipeDetailsViewModel.class);
        viewModel.getRecipe().observe(this, recipe -> {
            ingredientsRecyclerView.setAdapter(new IngredientsListAdapter(recipe.getIngredients()));
        });

    }
}
