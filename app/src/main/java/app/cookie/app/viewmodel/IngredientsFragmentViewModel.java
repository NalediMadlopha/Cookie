package app.cookie.app.viewmodel;


import android.os.Bundle;

import java.util.List;

import app.cookie.app._new_architecture.model.Ingredient;
import app.cookie.app.view.ingredients.IngredientFragmentView;

import static app.cookie.app._new_architecture.stringdef.CookieConstants.KEY.INGREDIENTS;

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
