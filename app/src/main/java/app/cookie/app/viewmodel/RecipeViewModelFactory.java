package app.cookie.app.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;


public class RecipeViewModelFactory implements ViewModelProvider.Factory {

    private RecipeViewModel viewModel;

    @Inject
    public RecipeViewModelFactory(RecipeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(RecipeViewModel.class)) {
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
