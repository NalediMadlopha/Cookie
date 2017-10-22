package app.cookie.app.view;


import java.util.List;

import app.cookie.app.model.Ingredient;
import app.cookie.app.model.Step;

public interface RecipeDetailsActivityView {

    void displayScreenTitle(String title);

    void showProgress();

    void hideProgress();

    void displayIngredients(List<Ingredient> ingredients);

    void displaySteps(int recipeId, String recipeName, List<Step> steps);
}
