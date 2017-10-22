package app.cookie.app.view;


import app.cookie.app.model.Step;

public interface StepDetailsActivityView {

    void displayScreenTitle(String title);

    void displayUI(int recipeId, int stepId);
}
