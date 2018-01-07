package com.cookie.app.viewmodel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import com.cookie.app.viewmodel.RecipeListViewModel;


public class RecipeListViewModelFactory implements ViewModelProvider.Factory {

    private RecipeListViewModel viewModel;

    @Inject
    public RecipeListViewModelFactory(RecipeListViewModel viewModel) {
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
