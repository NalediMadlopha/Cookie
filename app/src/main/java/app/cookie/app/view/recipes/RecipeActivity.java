package app.cookie.app.view.recipes;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cookie.app.R;

import javax.inject.Inject;

import app.cookie.app.dependency.App;
import app.cookie.app.viewmodel.RecipeViewModel;
import app.cookie.app.viewmodel.RecipeViewModelFactory;


public class RecipeActivity extends AppCompatActivity {

    @Inject RecipeViewModelFactory recipeViewModelFactory;
    private RecipeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        App.appComponent().inject(this);

        viewModel = ViewModelProviders.of(this, recipeViewModelFactory).get(RecipeViewModel.class);
    }
}
