package app.cookie.app.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import app.cookie.app.model.Recipe;

@Database(entities = {Recipe.class}, version = 1)
public abstract class CookieDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();
}
