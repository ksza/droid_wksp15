
package ro.ksza.wksp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ro.ksza.wksp.ui.WishListActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class WishListActivityTest {

    @Rule
    public ActivityTestRule<WishListActivity> mActivityRule = new ActivityTestRule<>(WishListActivity.class);

    @Test
    public void checkHelloWorldView() {
        // Find TextView and verify the correct text that is displayed
        onView(withId(R.id.search_button))
                .check(matches(withText(mActivityRule.getActivity().getString(R.string.hello_world))));
    }

    @Test
    public void findButtonPerformClickAndCheckChangedText() {
        // Find Button and Click on it
        onView(withId(R.id.search_button)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.search_movies_list))
                .atPosition(0).check(matches(withText("Movie no 0")));
    }
}