package app.cookie.app.view.recipes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cookie.app.R;

import app.cookie.app.viewmodel.RecipeViewModel;


public class RecipeFragment extends Fragment {

    private ProgressBar recipeProgressBar;
    private RecyclerView recipeRecyclerView;
    private RecipeViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        viewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
//        viewModel.init();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        viewModel.getRecipe().observe(this, recipes -> {
//            Toast.makeText(getContext(), recipes[0].getName(), Toast.LENGTH_SHORT).show();
//        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe, container, false);

//        recipeProgressBar = rootView.findViewById(R.id.recipe_progress_bar);
//        recipeRecyclerView = rootView.findViewById(R.id.recipe_recycler_view);
//
//        viewModel = new RecipeFragmentViewModel(getContext(), this, RetroClient.getApiService());
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        viewModel.onResume();
//    }
//
//    @Override
//    public void showProgressView() {
//        recipeProgressBar.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void hideProgressView() {
//        recipeProgressBar.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void displayRecipes(List<Recipe> recipeList) {
//        recipeRecyclerView.setAdapter(new RecipeAdapter(getContext(), recipeList));
//        recipeRecyclerView.setLayoutManager(new AutoGridLayoutManager(getContext(), getResources().getInteger(R.integer.recipe_card_width)));
//    }
}