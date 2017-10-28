package app.cookie.app.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import app.cookie.app.model.Recipe;
import dagger.Module;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Module
@Dao
public interface RecipeDao {

    @Insert(onConflict = REPLACE)
    void save(Recipe[] recipe);

    @Query("SELECT * FROM recipe")
    LiveData<Recipe[]> load();
}
