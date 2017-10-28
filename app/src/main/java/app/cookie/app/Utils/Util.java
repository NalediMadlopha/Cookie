package app.cookie.app.Utils;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import app.cookie.app.model.Recipe;
import app.cookie.app.model.Step;

public class Util {

    public static List<Recipe> getAllRecipes(Context context) {
        List<Recipe> recipeList = new ArrayList<>();

        try {
            InputStream inputStream = context.getAssets().open("recipes_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // TODO: 10/12/17 Check the value returned fromt the input stream
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            Type typeRecipes = new TypeToken<ArrayList<Recipe>>(){}.getType();
            recipeList = new Gson().fromJson(json, typeRecipes);
        } catch (IOException e) {
            Log.e(Util.class.getSimpleName(), e.getMessage(), e);
        }

        return recipeList;
    }

    public static Recipe getRecipe(Context context, int recipeId) {
        List<Recipe> recipeList = new ArrayList<>();

        try {
            InputStream inputStream = context.getAssets().open("recipes_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // TODO: 10/12/17 Check the value returned fromt the input stream
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            Type typeRecipes = new TypeToken<ArrayList<Recipe>>(){}.getType();
            recipeList = new Gson().fromJson(json, typeRecipes);
        } catch (IOException e) {
            Log.e(Util.class.getSimpleName(), e.getMessage(), e);
        }

        Recipe selectedRecipe = new Recipe();
        for (Recipe recipe : recipeList) {
            if (recipeId == recipe.getId()) {
                selectedRecipe = recipe;
                break;
            }
        }

        return selectedRecipe;
    }

    public static Step getRecipeStep(Context context,int recipeId, int stepId) {
        List<Recipe> recipeList = new ArrayList<>();

        try {
            InputStream inputStream = context.getAssets().open("recipes_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // TODO: 10/12/17 Check the value returned fromt the input stream
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            Type typeRecipes = new TypeToken<ArrayList<Recipe>>(){}.getType();
            recipeList = new Gson().fromJson(json, typeRecipes);
        } catch (IOException e) {
            Log.e(Util.class.getSimpleName(), e.getMessage(), e);
        }

        Recipe selectedRecipe = null;
        for (Recipe recipe : recipeList) {
            if (recipeId == recipe.getId()) {
                selectedRecipe = recipe;
                break;
            }
        }

        Step selectedStep = null;
        for (Step step : selectedRecipe.getSteps()) {
            if (stepId == step.getId()) {
                selectedStep = step;
                break;
            }
        }

        return selectedStep;
    }
}
