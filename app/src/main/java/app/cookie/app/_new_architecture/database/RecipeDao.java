package app.cookie.app._new_architecture.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import app.cookie.app._new_architecture.model.Recipe;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface RecipeDao {

    @Insert(onConflict = REPLACE)
    void insert(List<Recipe> recipes);

    @Query("SELECT * FROM recipe")
    LiveData<List<Recipe>> retrieve();
}
