package app.cookie.app._new_architecture.viewmodel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import app.cookie.app._new_architecture.viewmodel.RecipeDetailsViewModel;


public class RecipeDetailsViewModelFactory implements ViewModelProvider.Factory {

    private RecipeDetailsViewModel viewModel;

    @Inject
    public RecipeDetailsViewModelFactory(RecipeDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(RecipeDetailsViewModel.class)) {
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
