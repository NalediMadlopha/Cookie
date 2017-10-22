package app.cookie.app.stringdef;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static app.cookie.app.stringdef.CookieConstants.URL.BASE_URL;
import static app.cookie.app.stringdef.CookieConstants.URL.PATH_URL;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@StringDef({BASE_URL, PATH_URL})
public @interface CookieConstants {

    @interface URL {
        String BASE_URL = "http://d17h27t6h515a5.cloudfront.net";
        String PATH_URL = "/topher/2017/May/59121517_baking/baking.json";
    }

    @interface KEY {
        String RECIPE_ID = "recipe_id";
        String RECIPE_NAME = "recipe_name";
        String INGREDIENTS = "ingredients";
        String STEPS = "steps";
    }
}
