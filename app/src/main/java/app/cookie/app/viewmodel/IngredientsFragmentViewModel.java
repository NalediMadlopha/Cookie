package app.cookie.app.viewmodel;


import android.os.Bundle;

import java.util.List;

import app.cookie.app.model.Ingredient;
import app.cookie.app.view.IngredientFragmentView;

import static app.cookie.app.stringdef.CookieConstants.KEY.INGREDIENTS;

public class IngredientsFragmentViewModel {

    private final IngredientFragmentView view;
    private final Bundle bundle;

    public IngredientsFragmentViewModel(IngredientFragmentView view, Bundle bundle) {
        this.view = view;
        this.bundle = bundle;
    }

    public void onResume() {
        List<Ingredient> ingredients = bundle.getParcelableArrayList(INGREDIENTS);
        view.displayIngredients(ingredients);
    }
}
