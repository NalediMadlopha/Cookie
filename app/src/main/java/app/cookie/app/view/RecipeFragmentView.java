package app.cookie.app.view;


import java.util.List;

import app.cookie.app.model.Recipe;

public interface RecipeFragmentView {

    void showProgressView();

    void hideProgressView();

    void displayRecipes(List<Recipe> recipeList);
}
