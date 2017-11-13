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
import app.cookie.app._new_architecture.viewmodel.RecipeDetailsViewModel;
import app.cookie.app._new_architecture.viewmodel.factory.RecipeDetailsViewModelFactory;
import app.cookie.app.view.stepdetails.StepDetailsFragment;

import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_NAME;

public class RecipeDetailsActivity extends AppCompatActivity {

    @Inject
    RecipeDetailsViewModelFactory recipeDetailsViewModelFactory;
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
            Util.savePreferences(this, RECIPE_ID, recipeId);

            recipeName = getIntent().getExtras().getString(RECIPE_NAME);
            // TODO: 11/13/17 Replace recipe id with recipe name
            Util.savePreferences(this, RECIPE_NAME, recipeId);
        } else {
            SharedPreferences sharedPreferences = this.getPreferences(MODE_PRIVATE);
            recipeId = sharedPreferences.getInt(RECIPE_ID, 0);
            recipeName = String.valueOf(sharedPreferences.getInt(RECIPE_ID, 0));
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
            Bundle bundle = new Bundle();
            bundle.putInt(RECIPE_ID, recipeId);

            StepDetailsFragment stepDetailsFragment = new StepDetailsFragment();
            stepDetailsFragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .replace(R.id.step_details_container, stepDetailsFragment)
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