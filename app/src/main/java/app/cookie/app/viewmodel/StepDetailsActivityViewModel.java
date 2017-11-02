package app.cookie.app.viewmodel;


import android.os.Bundle;

import app.cookie.app.view.stepdetails.StepDetailsActivityView;

import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_ID;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.RECIPE_NAME;
import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.STEP_ID;

public class StepDetailsActivityViewModel {

    private final StepDetailsActivityView view;
    private final Bundle bundle;

    public StepDetailsActivityViewModel(StepDetailsActivityView view, Bundle bundle) {
        this.view = view;
        this.bundle = bundle;
    }

    public void onCreate() {
        view.displayScreenTitle(bundle.getString(RECIPE_NAME));
        view.displayUI(bundle.getInt(RECIPE_ID), bundle.getInt(STEP_ID));
    }
}
