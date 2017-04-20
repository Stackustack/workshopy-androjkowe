package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
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

import qaworkshops.android.netguru.co.qaworshopsandroid.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest2 {

    public static final String EMAIL_KEY = "email_key";

    private boolean initialised = false;

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule =
            new ActivityTestRule<>(
                    MainActivity.class,
                    true,     // initialTouchMode
                    false);   // launchActivity. False to customize the intent

    @Before
    public void beforeClassLogin() {
        if (initialised) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EMAIL_KEY, "john.doe@example.com");

        mMainActivityRule.launchActivity(intent);

        initialised = true;
    }

    @Test
    public void clickAddButton_opensAddItemUi() throws Exception {
        // Click on the add  button
        onView(withId(R.id.fab)).perform(click());

        // Check if the add item screen is displayed
        onView(withId(R.id.name_text_input_layout)).check(matches(isDisplayed()));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button2), withText("Cancel")));
        appCompatButton2.perform(scrollTo(), click());
    }

    @Test
    public void addItem_itemVisibleOnList() throws Exception {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withClassName(is("android.support.design.widget.TextInputEditText")), isDisplayed()));
        textInputEditText3.perform(click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withClassName(is("android.support.design.widget.TextInputEditText")), isDisplayed()));
        String itemName = "first" + System.currentTimeMillis();
        textInputEditText4.perform(replaceText(itemName), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("Create")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.item_title_text_view), withText(itemName)));
        relativeLayout.check(matches(isDisplayed()));
    }

    @Test
    public void addAndRemoveItem_itemNotVisibleOnList() throws Exception {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withClassName(is("android.support.design.widget.TextInputEditText")), isDisplayed()));
        textInputEditText3.perform(click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withClassName(is("android.support.design.widget.TextInputEditText")), isDisplayed()));
        String itemName = "other" + System.currentTimeMillis();
        textInputEditText4.perform(replaceText(itemName), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("Create")));
        appCompatButton2.perform(scrollTo(), click());


        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.item_title_text_view), withText(itemName)));
        appCompatImageView.perform(swipeLeft());

        ViewInteraction appCompatImageView2 = onView(
                allOf(withId(R.id.image_view),
                        withParent(allOf(withId(R.id.bottom_wrapper),
                                withParent(withId(R.id.swipe_layout)))),
                        isDisplayed()));
        appCompatImageView2.perform(click());

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.item_title_text_view), withText(itemName)));
        relativeLayout.check(doesNotExist());
    }

    @Test
    public void openDrawerAndEditProfile_checkValues() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Edit Profile"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.first_name), withText("John"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.first_name_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        editText.check(matches(withText("John")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.last_name), withText("Doe"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.last_name_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        editText2.check(matches(withText("Doe")));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.email), withText("john.doe@example.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.email_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        editText3.check(matches(withText("john.doe@example.com")));

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

