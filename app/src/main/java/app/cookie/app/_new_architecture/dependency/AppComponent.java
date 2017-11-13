package app.cookie.app._new_architecture.dependency;

import app.cookie.app._new_architecture.database.AppDatabase;
import app.cookie.app._new_architecture.repository.RecipeRepository;
import app.cookie.app._new_architecture.view.recipedetails.RecipeDetailsActivity;
import app.cookie.app._new_architecture.view.recipeslist.RecipeListActivity;
import app.cookie.app._new_architecture.viewmodel.RecipeDetailsViewModel;
import app.cookie.app._new_architecture.viewmodel.RecipeListViewModel;
import dagger.Component;

@Component(modules = AppModule.class )
public interface AppComponent {

    void inject(RecipeListActivity recipeListActivity);

    void inject(RecipeDetailsActivity recipeDetailsActivity);

    void inject(RecipeListViewModel viewModel);

    void inject(RecipeDetailsViewModel recipeDetailsViewModel);

    void inject(RecipeRepository recipeRepository);

    void inject(AppDatabase appDatabase);

}
