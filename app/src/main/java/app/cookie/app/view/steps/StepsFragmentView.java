package app.cookie.app.view.steps;


import java.util.List;

import app.cookie.app.model.Step;

public interface StepsFragmentView {
    void displaySteps(int recipeId, String recipeName, List<Step> steps);
}
