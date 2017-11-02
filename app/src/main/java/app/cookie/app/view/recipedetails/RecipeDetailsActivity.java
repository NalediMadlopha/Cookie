package app.cookie.app.view.recipedetails;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.cookie.app.R;

import java.util.ArrayList;
import java.util.List;

import app.cookie.app._new_architecture.model.Ingredient;
import app.cookie.app._new_architecture.model.Step;
import app.cookie.app.view.stepdetails.StepDetailsFragment;
import app.cookie.app.view.steps.StepsFragment;
import app.cookie.app.view.ingredients.IngredientsFragment;
import app.cookie.app.viewmodel.RecipeDetailsActivityViewModel;

import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.INGREDIENTS;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_NAME;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.STEPS;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.STEP_ID;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailsActivityView {

    private RecipeDetailsActivityViewModel viewModel;
    private ProgressBar progressBar;
    private FrameLayout ingredientsContainer;
    private FrameLayout stepsContainer;
    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        progressBar = findViewById(R.id.recipe_details_progress_bar);
        ingredientsContainer = findViewById(R.id.ingredient_list_container);
        stepsContainer = findViewById(R.id.step_list_container);

        viewModel = new RecipeDetailsActivityViewModel(this, getIntent().getExtras());
        viewModel.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume(this, getResources().getBoolean(R.bool.isTablet));
    }

    @Override
    public void displayScreenTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayIngredients(List<Ingredient> ingredients) {
        ingredientsContainer.setVisibility(View.VISIBLE);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(INGREDIENTS, (ArrayList) ingredients);

        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        ingredientsFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.ingredient_list_container, ingredientsFragment)
                .commit();
    }

    @Override
    public void displaySteps(int recipeId, String recipeName, List<Step> steps) {
        stepsContainer.setVisibility(View.VISIBLE);

        Bundle bundle = new Bundle();
        bundle.putString(RECIPE_NAME, recipeName);
        bundle.putInt(RECIPE_ID, recipeId);
        bundle.putParcelableArrayList(STEPS, (ArrayList) steps);

        StepsFragment stepsFragment = new StepsFragment();
        stepsFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.step_list_container, stepsFragment)
                .commit();
    }

    @Override
    public void displayStepDetails(int recipeId, String recipeName, int stepId) {
        Bundle bundle = new Bundle();
        bundle.putInt(RECIPE_ID, recipeId);
        bundle.putString(RECIPE_NAME, recipeName);
        bundle.putInt(STEP_ID, stepId);

        StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
        stepDetailsFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.step_details_container, stepDetailsFragment)
                .commit();
    }
}