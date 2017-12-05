package app.cookie.app._new_architecture.view.stepdetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.cookie.app.R;

import javax.inject.Inject;

import app.cookie.app.Utils.Util;
import app.cookie.app._new_architecture.dependency.App;
import app.cookie.app._new_architecture.viewmodel.factory.StepDetailsViewModelFactory;
import app.cookie.app.viewmodel.StepDetailsViewModel;

import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_NAME;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.STEP_ID;

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


//        StepDetailsActivityViewModel viewModel = new StepDetailsActivityViewModel(this, getIntent().getExtras());
//        viewModel.onCreate();
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
            // TODO: 11/13/17 Replace the recipe id with recipe name
            Util.saveStringPreferences(this, RECIPE_NAME, String.valueOf(recipeId));
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

//    @Override
//    public void displayScreenTitle(String title) {
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setHomeButtonEnabled(true);
//            getSupportActionBar().setTitle(title);
//        }
//    }
//
//    @Override
//    public void displayUI(int recipeId, int stepId) {
//        Bundle bundle = new Bundle();
//        bundle.putInt(RECIPE_ID, recipeId);
//        bundle.putInt(STEP_ID, stepId);
//
//        StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
//        stepDetailsFragment.setArguments(bundle);
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.step_details_container, stepDetailsFragment)
//                .commit();
//    }
}