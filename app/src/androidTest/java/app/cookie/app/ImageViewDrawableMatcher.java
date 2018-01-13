package app.cookie.app;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;

public class ImageViewDrawableMatcher {

        public static BoundedMatcher<View, ImageView> hasDrawable() {
            return new BoundedMatcher<View, ImageView>(ImageView.class) {
                @Override
                protected boolean matchesSafely(ImageView imageView) {
                    return imageView.getDrawable() != null;
                }

                @Override
                public void describeTo(Description description) {
                    description.appendText("has drawable");
                }
            };
        }

    }