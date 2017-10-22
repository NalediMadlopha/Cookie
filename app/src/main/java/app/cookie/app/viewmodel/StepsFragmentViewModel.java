package app.cookie.app.viewmodel;


import android.os.Bundle;

import java.util.List;

import app.cookie.app.model.Step;
import app.cookie.app.view.StepsFragmentView;

import static app.cookie.app.stringdef.CookieConstants.KEY.STEPS;

public class StepsFragmentViewModel {

    private final StepsFragmentView view;
    private final Bundle bundle;

    public StepsFragmentViewModel(StepsFragmentView view, Bundle bundle) {
        this.view = view;
        this.bundle = bundle;
    }

    public void onResume() {
        List<Step> steps = bundle.getParcelableArrayList(STEPS);
        view.displaySteps(steps);
    }
}