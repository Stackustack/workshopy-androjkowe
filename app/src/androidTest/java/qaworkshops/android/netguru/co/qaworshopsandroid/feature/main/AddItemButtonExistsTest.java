package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;


import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.User;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddItemButtonExistsTest {

    public static final String EMAIL_KEY = "email_key";

    private boolean initialised = false;

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule =
            new ActivityTestRule<>(
                    MainActivity.class,
                    true,     // initialTouchMode
                    false);   // launchActivity. False to customize the intent

    @Before
    public void clearItemsAndLogUser() {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(db -> {
                RealmResults<User> results = db.where(User.class).findAll();
                for (User user: results) {
                    user.setItemList(new RealmList<>());
                }
            });
        }

        if (initialised) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EMAIL_KEY, "john.doe@example.com");

        mMainActivityRule.launchActivity(intent);

        initialised = true;
    }

    @Test
    public void addItemButtonExistsAndAddsItemTest() {
        clickAddButton();

        // Input stuff in item input field
        onView(withClassName(is("android.support.design.widget.TextInputEditText")))
                .perform(click(), replaceText("Hello"), closeSoftKeyboard());
        // Click 'Create'
        onView(withText("Create")).perform(click());

        // ASSERTION: 'Hello' should be displayed
        ViewInteraction testItem = onView(withText("Hello"));

        testItem.check(matches(isDisplayed()));

        // Swipe item to delete
        testItem.perform(swipeLeft());

        // Click 'delete' icon
        onView(withId(R.id.image_view)).perform(click());

        // Element should be removed from view
        testItem.check(doesNotExist());

    }

    @Test
    public void addItemWithoutDescription_validationWarning() {
        clickAddButton();
        onView(withText(R.string.action_create)).perform(click());
        onView(withId(R.id.textinput_error)).check(matches(withText(R.string.error_field_required)));
    }

    @Test
    public void clickingBack_mainViewIsRenderedWithFabButton() {
        clickAddButton();
        pressBack();

        onView(withId(R.id.fab)).check(matches(isCompletelyDisplayed()));
    }


    public void clickAddButton() {
        // Check if 'Plus' sign is rendered and click it
        ViewInteraction addButton = onView(withId(R.id.fab));
        addButton.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
