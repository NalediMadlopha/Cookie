package app.cookie.app;


import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cookie.app.R;
import com.cookie.app.view.recipedetails.RecipeDetailsActivity;
import com.cookie.app.view.recipeslist.RecipeListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static app.cookie.app.ImageViewDrawableMatcher.hasDrawable;
import static com.cookie.app.stringdef.CookieConstants.KEY.RECIPE_ID;
import static com.cookie.app.stringdef.CookieConstants.KEY.RECIPE_NAME;

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest {

    int[] positions = {0, 1, 2, 3};

    @Rule public ActivityTestRule<RecipeListActivity> activityTestRule
            = new ActivityTestRule<>(RecipeListActivity.class);

    @Test
    public void should_display_a_recipe_recycler_view() throws Exception {
        onView(withId(R.id.recipe_recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void should_display_the_correct_recipe_name_on_the_card() {
        int recipeRecyclerView = R.id.recipe_recycler_view;

        onView(withRecyclerView(recipeRecyclerView)
                .atPositionOnView(positions[0], R.id.recipe_name_text_view))
                .check(matches(isDisplayed()))
                .check(matches(withText("Nutella Pie")));

        onView(withId(recipeRecyclerView)).perform(scrollToPosition(positions[1]));

        onView(withRecyclerView(recipeRecyclerView)
                .atPositionOnView(positions[1], R.id.recipe_name_text_view))
                .check(matches(isDisplayed()))
                .check(matches(withText("Brownies")));

        onView(withId(recipeRecyclerView)).perform(scrollToPosition(positions[2]));

        onView(withRecyclerView(recipeRecyclerView)
                .atPositionOnView(positions[2], R.id.recipe_name_text_view))
                .check(matches(isDisplayed()))
                .check(matches(withText("Yellow Cake")));

        onView(withId(recipeRecyclerView)).perform(scrollToPosition(positions[3]));

        onView(withRecyclerView(recipeRecyclerView)
                .atPositionOnView(positions[3], R.id.recipe_name_text_view))
                .check(matches(isDisplayed()))
                .check(matches(withText("Cheesecake")));
    }

    @Test
    public void recipe_card_image_view_should_display_an_image() throws Exception {
        int recipeRecyclerView = R.id.recipe_recycler_view;

        onView(withRecyclerView(recipeRecyclerView)
                .atPositionOnView(0, R.id.recipe_image_view))
                .check(matches(isDisplayed()))
                .check(matches(hasDrawable()));

        onView(withId(recipeRecyclerView)).perform(scrollToPosition(positions[1]));

        onView(withRecyclerView(recipeRecyclerView)
                .atPositionOnView(positions[1], R.id.recipe_image_view))
                .check(matches(isDisplayed()))
                .check(matches(hasDrawable()));

        onView(withId(recipeRecyclerView)).perform(scrollToPosition(positions[2]));

        onView(withRecyclerView(recipeRecyclerView)
                .atPositionOnView(positions[2], R.id.recipe_image_view))
                .check(matches(isDisplayed()))
                .check(matches(hasDrawable()));

        onView(withId(recipeRecyclerView)).perform(scrollToPosition(positions[3]));

        onView(withRecyclerView(recipeRecyclerView)
                .atPositionOnView(positions[3], R.id.recipe_image_view))
                .check(matches(isDisplayed()))
                .check(matches(hasDrawable()));

    }

    @Test
    public void navigate_to_the_recipe_details_activity_when_recipe_card_is_clicked() throws Exception {
       Intents.init();

        onView(withRecyclerView(R.id.recipe_recycler_view)
                .atPosition(0))
                .perform(click());

        intended(hasComponent(RecipeDetailsActivity.class.getName()));
        intended(hasExtra(RECIPE_ID, 1));
        intended(hasExtra(RECIPE_NAME, "Nutella Pie"));

        Intents.release();
    }

    private static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }
}
