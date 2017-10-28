package app.cookie.app.dependency;

import app.cookie.app.view.recipes.RecipeActivity;
import app.cookie.app.viewmodel.RecipeViewModel;
import dagger.Component;

@Component(modules = AppModule.class )
public interface AppComponent {

    void inject(RecipeActivity recipeActivity);

    void inject(RecipeViewModel viewModel);

}
