package app.cookie.app._new_architecture.dependency;

import app.cookie.app._new_architecture.repository.RecipeRepository;
import app.cookie.app._new_architecture.view.recipelist.RecipeListActivity;
import app.cookie.app._new_architecture.viewmodel.RecipeListViewModel;
import dagger.Component;

@Component(modules = AppModule.class )
public interface AppComponent {

    void inject(RecipeListActivity recipeListActivity);

    void inject(RecipeListViewModel viewModel);

    void inject(RecipeRepository recipeRepository);

}
