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
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
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

        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(db -> {
                RealmResults<User> results = db.where(User.class).findAll();
                for (User user: results) {
                    user.setItemList(new RealmList<>());
                }
            });
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
        String itemName = "first";
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
        String itemName = "other";
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

    @Test
    public void clickLogout_showLoginView() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Logout"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.sign_up_button),
                        childAtPosition(
                                allOf(withId(R.id.email_login_form),
                                        childAtPosition(
                                                withId(R.id.login_form),
                                                0)),
                                2),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.email_register_button),
                        childAtPosition(
                                allOf(withId(R.id.email_login_form),
                                        childAtPosition(
                                                withId(R.id.login_form),
                                                0)),
                                3),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
    }

    @Test
    public void editProfile_firstNameAboveLastName() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Edit Profile"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        onView(withId(R.id.first_name_text_input_layout))
                .check(isAbove(withId(R.id.last_name_text_input_layout)));

    }

    @Test
    public void genderIsSelectedTest() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Edit Profile"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.male_radio_button), withText("Male"),
                        withParent(allOf(withId(R.id.radio_group),
                                withParent(withId(R.id.email_register_form))))));

        appCompatRadioButton.check(matches(isChecked()));
    }

    @Test
    public void modalCloseOnBackButtonTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton.perform(click());

        pressBack();

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.fab), isDisplayed()));
        imageButton2.check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void newItemWithEmptyTextTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("Create")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textinput_error), withText(R.string.error_field_required),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.name_text_input_layout),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText(R.string.error_field_required)));

    }

    @Test
    public void toolbarTextTest() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.toolbar))));
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

