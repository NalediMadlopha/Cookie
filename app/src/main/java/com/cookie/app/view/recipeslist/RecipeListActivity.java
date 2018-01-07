package com.cookie.app.view.recipeslist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cookie.app.R;
import com.cookie.app.dependency.App;
import com.cookie.app.viewmodel.RecipeListViewModel;
import com.cookie.app.viewmodel.factory.RecipeListViewModelFactory;

import javax.inject.Inject;

public class RecipeListActivity extends AppCompatActivity {

    @Inject
    RecipeListViewModelFactory recipeListViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        App.appComponent().inject(this);

        ViewModelProviders.of(this, recipeListViewModelFactory).get(RecipeListViewModel.class);
    }
}