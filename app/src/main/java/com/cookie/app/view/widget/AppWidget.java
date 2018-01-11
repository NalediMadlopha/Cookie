package com.cookie.app.view.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.cookie.app.R;
import com.cookie.app.Utils.Util;

public class AppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, AppWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
            views.setTextViewText(R.id.appwidget_text, Util.getAppWidgetRecipeNamePreference(context));
            views.setEmptyView(R.id.appwidget_ingredient_list, R.id.empty_view);
            views.setRemoteAdapter(appWidgetId, R.id.appwidget_ingredient_list, intent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        final String action = intent.getAction();
        if (action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int[] ids = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                appWidgetManager.notifyAppWidgetViewDataChanged(ids, R.id.appwidget_ingredient_list);
            }
            String message = context.getResources().getString(R.string.widget_recipe_pinned, Util.getAppWidgetRecipeNamePreference(context));
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
        super.onReceive(context, intent);
    }
}

