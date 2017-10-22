package app.cookie.app.viewmodel;


import app.cookie.app.view.RecipeActivityView;

public class RecipeActivityViewModel {

    private final RecipeActivityView view;

    public RecipeActivityViewModel(RecipeActivityView view) {
        this.view = view;
    }

    public void onCreate() {
        view.displayUI();
    }
}
