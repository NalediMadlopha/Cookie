package com.cookie.app.stringdef;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static com.cookie.app.stringdef.CookieConstants.URL.BASE_URL;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@StringDef({BASE_URL})
public @interface CookieConstants {

    @interface URL {
        String BASE_URL = "http://d17h27t6h515a5.cloudfront.net";
        String PATH_URL = "/topher/2017/May/59121517_baking/baking.json";
    }

    @interface KEY {
        String RECIPE_ID = "recipe_id";
        String RECIPE_NAME = "recipe_name";
        String STEP_ID = "step_id";
        String APP_WIDGET_PREFERENCES = "app_widget_preferences";
        String APP_WIDGET_RECIPE_NAME_PREFERENCES = "app_widget_recipe_name_preferences";
        String APP_WIDGET_INGREDIENTS_PREFERENCES = "app_widget_ingredients_preferences";
    }
}
