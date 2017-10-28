package app.cookie.app._new_architecture.view.recipelist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cookie.app.R;

import java.util.List;

import app.cookie.app._new_architecture.model.Recipe;
import app.cookie.app._new_architecture.viewmodel.RecipeListViewModel;


public class RecipeListFragment extends android.support.v4.app.Fragment {

    private RecipeListViewModel viewModel;
    private RecyclerView recipeRecyclerView;
    private List<Recipe> recipes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        recipeRecyclerView = rootView.findViewById(R.id.recipe_recycler_view);
        recipeRecyclerView.setLayoutManager(new AutoGridLayoutManager(getContext(), getResources().getInteger(R.integer.recipe_card_width)));

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(RecipeListViewModel.class);
        viewModel.init();
        viewModel.getRecipes().observe(this, recipes -> {
            recipeRecyclerView.setAdapter(new RecipeListAdapter(getContext(), recipes));
        });
    }
}