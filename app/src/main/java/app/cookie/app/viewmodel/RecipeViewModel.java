package app.cookie.app.viewmodel;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import app.cookie.app.dependency.App;
import app.cookie.app.repository.RecipeRepository;

public class RecipeViewModel extends ViewModel {

    RecipeRepository repository;

    @Inject
    public RecipeViewModel(RecipeRepository repository) {
        App.appComponent().inject(this);
        this.repository = repository;
    }

    public String getRecipe() {
        return repository.getRecipe();
    }
}
