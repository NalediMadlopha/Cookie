package com.cookie.app.Utils;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.cookie.app.model.Ingredient;
import com.cookie.app.model.Recipe;
import com.cookie.app.model.Step;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.cookie.app.stringdef.CookieConstants.KEY.APP_WIDGET_INGREDIENTS_PREFERENCES;
import static com.cookie.app.stringdef.CookieConstants.KEY.APP_WIDGET_PREFERENCES;
import static com.cookie.app.stringdef.CookieConstants.KEY.APP_WIDGET_RECIPE_NAME_PREFERENCES;

public class Util {

    private Util() {
    }

    public static void saveIntPreferences(Activity activity, String tag, int value) {
        SharedPreferences sharedPreferences;
        sharedPreferences = activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(tag, value);
        editor.apply();
    }

    public static void saveStringPreferences(Activity activity, String tag, String value) {
        SharedPreferences sharedPreferences;
        sharedPreferences = activity.getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tag, value);
        editor.apply();
    }

    public static void saveAppWidgetPreferences(Context context, Recipe recipe) {
        Type typeIngredients = new TypeToken<ArrayList<Ingredient>>(){}.getType();
        String ingredientsString = new Gson().toJson(recipe.getIngredients(), typeIngredients);

        context.getSharedPreferences(APP_WIDGET_PREFERENCES, MODE_PRIVATE)
                .edit()
                .putString(APP_WIDGET_RECIPE_NAME_PREFERENCES, recipe.getName())
                .putString(APP_WIDGET_INGREDIENTS_PREFERENCES, ingredientsString)
                .apply();
    }

    public static List<Ingredient> getAppWidgetPreferences(Context context) {
        String json = context.getSharedPreferences(APP_WIDGET_PREFERENCES, MODE_PRIVATE)
                .getString(APP_WIDGET_INGREDIENTS_PREFERENCES, "");

        Type typeIngredients = new TypeToken<ArrayList<Ingredient>>(){}.getType();
        return new Gson().fromJson(json, typeIngredients);
    }

    public static String getAppWidgetRecipeNamePreference(Context context) {
        return context.getSharedPreferences(APP_WIDGET_PREFERENCES, MODE_PRIVATE).getString(APP_WIDGET_RECIPE_NAME_PREFERENCES, "");
    }

    public static List<Recipe> getAllRecipes(Context context) {
        List<Recipe> recipeList = new ArrayList<>();

        try {
            InputStream inputStream = context.getAssets().open("recipes_data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // TODO: 10/12/17 Check the value returned from the input stream
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
