package app.cookie.app.dependency;

import app.cookie.app.database.AppDatabase;
import app.cookie.app.repository.RecipeRepository;
import app.cookie.app.view.recipedetails.RecipeDetailsActivity;
import app.cookie.app.view.recipeslist.RecipeListActivity;
import app.cookie.app.view.stepdetails.StepDetailsActivity;
import app.cookie.app.view.stepdetails.StepDetailsFragment;
import app.cookie.app.viewmodel.RecipeDetailsViewModel;
import app.cookie.app.viewmodel.RecipeListViewModel;
import app.cookie.app.viewmodel.StepDetailsViewModel;
import dagger.Component;

@Component(modules = AppModule.class )
public interface AppComponent {

    void inject(RecipeListActivity recipeListActivity);

    void inject(RecipeDetailsActivity recipeDetailsActivity);

    void inject(StepDetailsActivity stepDetailsActivity);

    void inject(StepDetailsFragment stepDetailsFragment);

    void inject(RecipeListViewModel viewModel);

    void inject(RecipeDetailsViewModel recipeDetailsViewModel);

    void inject(StepDetailsViewModel stepDetailsViewModel);

    void inject(RecipeRepository recipeRepository);

    void inject(AppDatabase appDatabase);

}
