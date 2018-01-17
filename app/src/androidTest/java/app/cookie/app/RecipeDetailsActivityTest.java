package app.cookie.app;


import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cookie.app.R;
import com.cookie.app.view.recipedetails.RecipeDetailsActivity;
import com.cookie.app.view.stepdetails.StepDetailsActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static com.cookie.app.stringdef.CookieConstants.KEY.RECIPE_ID;
import static com.cookie.app.stringdef.CookieConstants.KEY.RECIPE_NAME;
import static com.cookie.app.stringdef.CookieConstants.KEY.STEP_ID;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailsActivityTest {

    @Rule
    public ActivityTestRule<RecipeDetailsActivity> activityTestRule
            = new ActivityTestRule<>(RecipeDetailsActivity.class);


    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @Test
    public void navigate_to_step_details_activity_when_a_step_item_is_clicked() throws Exception {
        int stepsRecyclerView = R.id.steps_recycler_view;
        onView(withRecyclerView(stepsRecyclerView)
                .atPosition(0))
                .perform(click());

        intended(hasComponent(StepDetailsActivity.class.getName()));
        intended(hasExtra(RECIPE_ID, 1));
        intended(hasExtra(RECIPE_NAME, "Nutella Pie"));
        intended(hasExtra(STEP_ID, 0));
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

    private static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }
}