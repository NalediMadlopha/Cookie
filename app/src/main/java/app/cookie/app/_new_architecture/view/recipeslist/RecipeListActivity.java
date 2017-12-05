package app.cookie.app._new_architecture.view.recipeslist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cookie.app.R;

import javax.inject.Inject;

import app.cookie.app._new_architecture.dependency.App;
import app.cookie.app._new_architecture.viewmodel.RecipeListViewModel;
import app.cookie.app._new_architecture.viewmodel.factory.RecipeListViewModelFactory;

public class RecipeListActivity extends AppCompatActivity {

    @Inject
    RecipeListViewModelFactory recipeListViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        App.appComponent().inject(this);

//        RecipeListViewModel recipeListViewModel = new RecipeListViewModel(new RecipeRepository());
//        recipeListViewModel.offlineData(this);

        ViewModelProviders.of(this, recipeListViewModelFactory).get(RecipeListViewModel.class);
    }
}