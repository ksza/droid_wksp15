
package ro.ksza.wksp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ro.ksza.wksp.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkHelloWorldView() {
        // Find TextView and verify the correct text that is displayed
        onView(withId(R.id.hello_text_view))
                .check(matches(withText(mActivityRule.getActivity().getString(R.string.hello_world))));
    }

    @Test
    public void findButtonPerformClickAndCheckChangedText() {
        // Find Button and Click on it
        onView(withId(R.id.button)).perform(click());

        // Find TextView and verify the correct text that is displayed
        onView(withId(R.id.hello_text_view))
                .check(matches(withText(mActivityRule.getActivity().getString(R.string.changed_hello_world))));
    }
}