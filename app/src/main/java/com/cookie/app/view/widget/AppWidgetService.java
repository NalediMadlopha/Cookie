package com.cookie.app.view.widget;


import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.cookie.app.R;
import com.cookie.app.Utils.Util;
import com.cookie.app.model.Ingredient;

import java.util.List;

public class AppWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteIngredientsItemViewFactory(this.getApplicationContext());
    }

    public class RemoteIngredientsItemViewFactory implements RemoteViewsService.RemoteViewsFactory {

        private List<Ingredient> ingredients;
        private final Context context;

        public RemoteIngredientsItemViewFactory(Context context) {
            this.context = context;
        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            ingredients = Util.getAppWidgetPreferences(context);
        }

        @Override
        public void onDestroy() {
        }

        @Override
        public int getCount() {
            return ingredients.size();
        }

        @Override
        public RemoteViews getViewAt(int i) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_ingredient_item);
            CharSequence ingredientName = getString(R.string.add_widget_item_format,
                                                ingredients.get(i).getQuantity(),
                                                ingredients.get(i).getMeasure(),
                                                ingredients.get(i).getIngredient());
            views.setTextViewText(R.id.ingredient_text_view, ingredientName);

            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return ingredients.get(i).getId();
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
