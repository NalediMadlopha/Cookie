package app.cookie.app.view.recipes;


import java.util.List;

import app.cookie.app._new_architecture.model.Recipe;

public interface RecipeFragmentView {

    void showProgressView();

    void hideProgressView();

    void displayRecipes(List<Recipe> recipeList);
}
