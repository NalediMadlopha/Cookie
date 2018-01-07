package com.cookie.app.view.widget;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;


public class RecipeDataProvider extends ContentProvider {

    public static final Uri CONTENT_URI = Uri.parse("content://com.cookie.app.ingredientslistwidget.provider");
    public static class Columns {
        public static final String ID = "_id";
        public static final String INGREDIENT = "ingredient";
        public static final String MEASURE = "measure";
        public static final String QUANTITY = "quantity";
    }

    private static final ArrayList<IngredientDataPoint> data = new ArrayList<>();


    @Override
    public boolean onCreate() {
        data.add(new IngredientDataPoint(1, "Ingredients Title1", "1.5", "ml", 1));
        data.add(new IngredientDataPoint(2, "Ingredients Title2", "1.5", "ml", 1));
        data.add(new IngredientDataPoint(3, "Ingredients Title3", "1.5", "ml", 1));
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        final MatrixCursor cursor = new MatrixCursor(
                new String[]{Columns.ID, Columns.INGREDIENT, Columns.MEASURE, Columns.QUANTITY});
        for (int i = 0; i < data.size(); ++i) {
            final IngredientDataPoint ingredient = data.get(i);
            cursor.addRow(new Object[]{new Integer(i), ingredient.ingredient, ingredient.measure, ingredient.quantity});
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "vnd.cookie.cursor.dir/vnd.ingredientslistwidget.ingredients";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        final int index = Integer.parseInt(uri.getPathSegments().get(0));
        final MatrixCursor c = new MatrixCursor(
                new String[]{Columns.ID, Columns.INGREDIENT, Columns.MEASURE, Columns.QUANTITY});
        assert(0 <= index && index < data.size());
        final IngredientDataPoint ingredient = data.get(index);

        ingredient.ingredient = contentValues.getAsString(Columns.INGREDIENT);
        ingredient.quantity = contentValues.getAsString(Columns.QUANTITY);
        ingredient.measure = contentValues.getAsString(Columns.MEASURE);

        return 0;
    }

    class IngredientDataPoint {

        int id;
        String ingredient;
        String quantity;
        String measure;
        int recipeId;

        public IngredientDataPoint(int id, String ingredient, String quantity, String measure, int recipeId) {
            this.id = id;
            this.ingredient = ingredient;
            this.quantity = quantity;
            this.measure = measure;
            this.recipeId = recipeId;
        }
    }
}
