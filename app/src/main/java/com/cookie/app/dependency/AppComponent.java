package com.cookie.app.dependency;

import com.cookie.app.database.AppDatabase;
import com.cookie.app.repository.RecipeRepository;
import com.cookie.app.view.recipedetails.RecipeDetailsActivity;
import com.cookie.app.view.recipeslist.RecipeListActivity;
import com.cookie.app.view.stepdetails.StepDetailsActivity;
import com.cookie.app.view.stepdetails.StepDetailsFragment;
import com.cookie.app.view.widget.AppWidget;
import com.cookie.app.view.widget.AppWidgetService;
import com.cookie.app.viewmodel.RecipeDetailsViewModel;
import com.cookie.app.viewmodel.RecipeListViewModel;
import com.cookie.app.viewmodel.StepDetailsViewModel;

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

    void inject(AppWidget appWidget);

    void inject(AppWidgetService appWidgetService);
}
