package app.cookie.app._new_architecture.view.recipedetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.cookie.app.R;

import javax.inject.Inject;

import app.cookie.app.Utils.Util;
import app.cookie.app._new_architecture.dependency.App;
import app.cookie.app._new_architecture.view.recipedetails.ingredientslist.IngredientsListFragment;
import app.cookie.app._new_architecture.view.recipedetails.stepslist.StepsListFragment;
import app.cookie.app._new_architecture.view.stepdetails.StepDetailsFragment;
import app.cookie.app._new_architecture.viewmodel.RecipeDetailsViewModel;
import app.cookie.app._new_architecture.viewmodel.factory.RecipeDetailsViewModelFactory;
import app.cookie.app._new_architecture.viewmodel.factory.StepDetailsViewModelFactory;
import app.cookie.app.viewmodel.StepDetailsViewModel;

import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_NAME;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Inject
    RecipeDetailsViewModelFactory recipeDetailsViewModelFactory;
    @Inject
    StepDetailsViewModelFactory stepDetailsViewModelFactory;
    private int recipeId;
    private String recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        App.appComponent().inject(this);

        setupScreenData();
        setupViewModel();
        setupUI();
    }

    private void setupScreenData() {
        if (getIntent().getExtras() != null) {
            recipeId = getIntent().getExtras().getInt(RECIPE_ID);
            Util.saveIntPreferences(this, RECIPE_ID, recipeId);
            recipeName = getIntent().getExtras().getString(RECIPE_NAME);
            // TODO: 11/13/17 Replace the recipe id with recipe name
            Util.saveStringPreferences(this, RECIPE_NAME, "");
        } else {
            SharedPreferences sharedPreferences = this.getPreferences(MODE_PRIVATE);
            recipeId = sharedPreferences.getInt(RECIPE_ID, 0);
            recipeName = sharedPreferences.getString(RECIPE_NAME, "");
        }
    }

    private void setupViewModel() {
        RecipeDetailsViewModel viewModel = ViewModelProviders.of(this, recipeDetailsViewModelFactory).get(RecipeDetailsViewModel.class);
        viewModel.init(recipeId);
    }

    private void setupUI() {
        setupScreenTitle(recipeName);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.ingredient_list_container, new IngredientsListFragment())
                .commit();

        fragmentManager.beginTransaction()
                .replace(R.id.step_list_container, new StepsListFragment())
                .commit();

        boolean isTablet = getResources().getBoolean(R.bool.isTablet);
        if (isTablet) {
            StepDetailsViewModel viewModel = ViewModelProviders.of(this, stepDetailsViewModelFactory).get(StepDetailsViewModel.class);
            viewModel.init(recipeId, 0);

            fragmentManager.beginTransaction()
                    .replace(R.id.step_details_container, new StepDetailsFragment())
                    .commit();
        }
    }

    public void setupScreenTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }
}