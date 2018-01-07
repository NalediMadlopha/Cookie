package com.cookie.app.view.recipedetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.cookie.app.R;
import com.cookie.app.Utils.Util;
import com.cookie.app.dependency.App;
import com.cookie.app.view.recipedetails.ingredientslist.IngredientsListFragment;
import com.cookie.app.view.recipedetails.stepslist.StepsListFragment;
import com.cookie.app.view.stepdetails.StepDetailsFragment;
import com.cookie.app.viewmodel.RecipeDetailsViewModel;
import com.cookie.app.viewmodel.StepDetailsViewModel;
import com.cookie.app.viewmodel.factory.RecipeDetailsViewModelFactory;
import com.cookie.app.viewmodel.factory.StepDetailsViewModelFactory;

import javax.inject.Inject;

import static com.cookie.app.stringdef.CookieConstants.KEY.RECIPE_ID;
import static com.cookie.app.stringdef.CookieConstants.KEY.RECIPE_NAME;

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
            Util.saveStringPreferences(this, RECIPE_NAME, recipeName);
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

        if (getResources().getBoolean(R.bool.isTablet)) {
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