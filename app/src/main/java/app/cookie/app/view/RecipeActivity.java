package app.cookie.app.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.cookie.app.R;

import app.cookie.app.viewmodel.RecipeActivityViewModel;


public class RecipeActivity extends AppCompatActivity implements RecipeActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        RecipeActivityViewModel viewModel = new RecipeActivityViewModel(this);
        viewModel.onCreate();
    }

    @Override
    public void displayUI() {
        RecipeFragment recipeFragment = new RecipeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.recipe_list_container, recipeFragment)
                .commit();
    }
}
