package app.cookie.app.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import app.cookie.app._new_architecture.viewmodel.RecipeListViewModel;


public class RecipeViewModelFactory implements ViewModelProvider.Factory {

    private RecipeListViewModel viewModel;

    @Inject
    public RecipeViewModelFactory(RecipeListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(RecipeListViewModel.class)) {
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
