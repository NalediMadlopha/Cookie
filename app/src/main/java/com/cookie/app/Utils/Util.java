package com.cookie.app.Utils;


import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.cookie.app.R;
import com.cookie.app.model.Ingredient;
import com.cookie.app.model.Recipe;
import com.cookie.app.view.widget.AppWidget;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.cookie.app.database.Converters.ingredientsListToString;
import static com.cookie.app.database.Converters.ingredientsStringToList;
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
        String ingredientsString = ingredientsListToString(recipe.getIngredients());

        context.getSharedPreferences(APP_WIDGET_PREFERENCES, MODE_PRIVATE)
                .edit()
                .putString(APP_WIDGET_RECIPE_NAME_PREFERENCES, recipe.getName())
                .putString(APP_WIDGET_INGREDIENTS_PREFERENCES, ingredientsString)
                .apply();
    }

    public static List<Ingredient> getAppWidgetPreferences(Context context) {
        String json = context.getSharedPreferences(APP_WIDGET_PREFERENCES, MODE_PRIVATE)
                .getString(APP_WIDGET_INGREDIENTS_PREFERENCES, "");

        if (TextUtils.isEmpty(json)) {
            return new ArrayList<>();
        }
        return ingredientsStringToList(json);
    }

    public static String getAppWidgetRecipeNamePreference(Context context) {
        String recipeName = context.getSharedPreferences(APP_WIDGET_PREFERENCES, MODE_PRIVATE).getString(APP_WIDGET_RECIPE_NAME_PREFERENCES, "");

        if (TextUtils.isEmpty(recipeName)) {
            return context.getString(R.string.ingredients);
        }
        return recipeName;
    }

    public static void updateWidgets(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] ids = appWidgetManager.getAppWidgetIds(new ComponentName(context, AppWidget.class));

        Intent intent = new Intent(context.getApplicationContext(), AppWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(intent);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            return null;
        }
    }
}
