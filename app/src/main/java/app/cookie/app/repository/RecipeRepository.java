package app.cookie.app.repository;


import javax.inject.Singleton;

@Singleton
public class RecipeRepository {

    public RecipeRepository() {
    }

    public String getRecipe() {
        return "Recipe";
    }
}
