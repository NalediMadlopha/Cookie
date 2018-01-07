package com.cookie.app.view.stepdetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.cookie.app.R;

import javax.inject.Inject;

import com.cookie.app.Utils.Util;
import com.cookie.app.dependency.App;
import com.cookie.app.viewmodel.factory.StepDetailsViewModelFactory;
import com.cookie.app.viewmodel.StepDetailsViewModel;

import static com.cookie.app.stringdef.CookieConstants.KEY.RECIPE_ID;
import static com.cookie.app.stringdef.CookieConstants.KEY.RECIPE_NAME;
import static com.cookie.app.stringdef.CookieConstants.KEY.STEP_ID;

public class StepDetailsActivity extends AppCompatActivity {

    @Inject
    StepDetailsViewModelFactory stepDetailsViewModelFactory;
    private int recipeId;
    private String recipeName;
    private int stepId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);
        App.appComponent().inject(this);

        setupScreenData();
        setupViewModel();
        setupUI();
    }

    private void setupViewModel() {
        StepDetailsViewModel viewModel = ViewModelProviders.of(this, stepDetailsViewModelFactory).get(StepDetailsViewModel.class);
        viewModel.init(recipeId, stepId);
    }

    private void setupScreenData() {
        if (getIntent().getExtras() != null) {
            recipeId = getIntent().getExtras().getInt(RECIPE_ID);
            Util.saveIntPreferences(this, RECIPE_ID, recipeId);
            recipeName = getIntent().getExtras().getString(RECIPE_NAME);
            Util.saveStringPreferences(this, RECIPE_NAME, recipeName);
            stepId = getIntent().getExtras().getInt(STEP_ID, stepId);
        } else {
            SharedPreferences sharedPreferences = this.getPreferences(MODE_PRIVATE);
            recipeId = sharedPreferences.getInt(RECIPE_ID, 0);
            recipeName = sharedPreferences.getString(RECIPE_NAME, "");
            stepId = sharedPreferences.getInt(STEP_ID, 0);
        }
    }

    public void setupScreenTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(title);
        }
    }

    private void setupUI() {
        setupScreenTitle(recipeName);

        StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.step_details_container, stepDetailsFragment)
                .commit();
    }
}