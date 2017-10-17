package app.cookie.app.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cookie.app.R;

import java.util.List;

import app.cookie.app.data.RetroClient;
import app.cookie.app.model.Recipe;
import app.cookie.app.viewmodel.RecipeFragmentViewModel;


public class RecipeFragment extends Fragment implements RecipeFragmentView {

    private ProgressBar recipeProgressBar;
    private RecyclerView recipeRecyclerView;
    private RecipeFragmentViewModel viewModel;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        recipeProgressBar = rootView.findViewById(R.id.recipe_progress_bar);
        recipeRecyclerView = rootView.findViewById(R.id.recipe_recycler_view);

        viewModel = new RecipeFragmentViewModel(getContext(), this, RetroClient.getApiService());

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    public void showProgressView() {
        recipeProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressView() {
        recipeProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayRecipes(List<Recipe> recipeList) {
        recipeRecyclerView.setAdapter(new RecipeAdapter(getContext(), recipeList));
        recipeRecyclerView.setLayoutManager(new AutoGridLayoutManager(getContext(), getResources().getInteger(R.integer.recipe_card_width)));
    }
}