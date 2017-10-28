package app.cookie.app._new_architecture.view.recipelist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cookie.app.R;

import javax.inject.Inject;

import app.cookie.app._new_architecture.viewmodel.RecipeListViewModel;
import app.cookie.app._new_architecture.dependency.App;
import app.cookie.app.viewmodel.RecipeViewModelFactory;


public class RecipeListActivity extends AppCompatActivity {

    @Inject RecipeViewModelFactory recipeViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        App.appComponent().inject(this);

        ViewModelProviders.of(this, recipeViewModelFactory).get(RecipeListViewModel.class);
    }
}
