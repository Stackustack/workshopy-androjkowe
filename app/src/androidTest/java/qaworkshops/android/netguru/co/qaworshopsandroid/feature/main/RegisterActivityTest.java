package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.login.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void registerActivityTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.email_register_button), withText("Do not have an account? Register"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction textInputEditText = onView(
                withId(R.id.first_name));
        textInputEditText.perform(scrollTo(), replaceText("Test"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                withId(R.id.last_name));
        textInputEditText2.perform(scrollTo(), replaceText("Example"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                withId(R.id.email));
        textInputEditText3.perform(scrollTo(), replaceText("test@example.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText4 = onView(
                withId(R.id.password));
        textInputEditText4.perform(scrollTo(), replaceText("test123456"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.set_birthday_button), withText("Set birthdate"),
                        withParent(allOf(withId(R.id.email_register_form),
                                withParent(withId(R.id.register_form))))));
        appCompatButton2.perform(scrollTo(), click());

//        ViewInteraction appCompatTextView = onView(
//                allOf(withClassName(is("android.support.v7.widget.AppCompatTextView")), withText("2017"),
//                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
//                                withParent(withClassName(is("android.widget.LinearLayout"))))),
//                        isDisplayed()));
//        appCompatTextView.perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1992, 11, 14));
        onView(withId(android.R.id.button1)).perform(click());

//        ViewInteraction textViewWithCircularIndicator = onView(
//                allOf(withClassName(is("android.widget.TextViewWithCircularIndicator"))));
//        textViewWithCircularIndicator.perform(PickerActions.setDate(1995, 11, 14));
//        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())).perform(PickerActions.setDate(1995, 11, 14));

//        ViewInteraction appCompatButton3 = onView(
//                allOf(withId(android.R.id.button1), withText("OK"),
//                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
//                                withParent(withClassName(is("android.widget.LinearLayout"))))),
//                        isDisplayed()));
//        appCompatButton3.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.select_country_spinner),
                        withParent(allOf(withId(R.id.email_register_form),
                                withParent(withId(R.id.register_form))))));
        appCompatSpinner.perform(scrollTo(), click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(android.R.id.text1), withText("USA"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.female_radio_button), withText("Female"),
                        withParent(allOf(withId(R.id.radio_group),
                                withParent(withId(R.id.email_register_form))))));
        appCompatRadioButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.sign_up_button), withText("Register"),
                        withParent(allOf(withId(R.id.email_register_form),
                                withParent(withId(R.id.register_form))))));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_layout),
                                        0),
                                2),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

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
