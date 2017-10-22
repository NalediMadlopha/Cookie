package app.cookie.app.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.cookie.app.R;

import java.util.ArrayList;
import java.util.List;

import app.cookie.app.model.Ingredient;
import app.cookie.app.model.Step;
import app.cookie.app.viewmodel.RecipeDetailsActivityViewModel;

import static app.cookie.app.stringdef.CookieConstants.KEY.INGREDIENTS;
import static app.cookie.app.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app.stringdef.CookieConstants.KEY.RECIPE_NAME;
import static app.cookie.app.stringdef.CookieConstants.KEY.STEPS;

public class RecipeDetailsActivity extends AppCompatActivity implements RecipeDetailsActivityView {

    private RecipeDetailsActivityViewModel viewModel;
    private ProgressBar progressBar;
    private FrameLayout ingredientsContainer;
    private FrameLayout stepsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        progressBar = findViewById(R.id.recipe_details_progress_bar);
        ingredientsContainer = findViewById(R.id.ingredient_list_container);
        stepsContainer = findViewById(R.id.step_list_container);

        viewModel = new RecipeDetailsActivityViewModel(this, getIntent().getIntExtra(RECIPE_ID, 0));
        viewModel.onCreate(getIntent().getStringExtra(RECIPE_NAME));
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume(this);
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
    public void displaySteps(List<Step> steps) {
        stepsContainer.setVisibility(View.VISIBLE);

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(STEPS, (ArrayList) steps);

        StepsFragment stepsFragment = new StepsFragment();
        stepsFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.step_list_container, stepsFragment)
                .commit();
    }

}
