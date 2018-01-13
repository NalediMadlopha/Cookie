package app.cookie.app;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cookie.app.R;
import com.cookie.app.view.stepdetails.StepDetailsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class StepDetailsActivityTest {

    @Rule
    public ActivityTestRule<StepDetailsActivity> activityTestRule
            = new ActivityTestRule<>(StepDetailsActivity.class);

    @Test
    public void should_display_the_next_button() throws Exception {
        onView(withId(R.id.next_step_button))
                .check(matches(isDisplayed()))
                .check(matches(withText("Next")));
    }

    @Test
    public void should_not_display_the_previous_button() throws Exception {
        onView(withId(R.id.previous_step_button))
                .check(matches(not(isDisplayed())))
                .check(matches(withText("Previous")));
    }

    @Test
    public void should_display_the_previous_button_when_the_next_button_is_clicked() throws Exception {
        onView(withId(R.id.next_step_button))
                .perform(click());

        onView(withId(R.id.previous_step_button))
                .check(matches(isDisplayed()))
                .check(matches(withText("Previous")));
    }
}
