package com.cookie.app.viewmodel.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import com.cookie.app.viewmodel.StepDetailsViewModel;


public class StepDetailsViewModelFactory implements ViewModelProvider.Factory {

    private StepDetailsViewModel viewModel;

    @Inject
    public StepDetailsViewModelFactory(StepDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(StepDetailsViewModel.class)) {
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
