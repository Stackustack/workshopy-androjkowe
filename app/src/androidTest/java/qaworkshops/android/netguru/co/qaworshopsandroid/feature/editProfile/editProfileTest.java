package qaworkshops.android.netguru.co.qaworshopsandroid.feature.editProfile;


import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.User;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class editProfileTest {

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

        navigateToEditProfileView();
    }

    public void navigateToEditProfileView() {
        // Click 'Hamburger' menu icon
        onView(withContentDescription("Open navigation drawer")).perform(click());

        // Click 'Edit Profile' (clicking by ID doesnt work)
        onView(withText(R.string.edit_profile)).perform(click());

        ViewInteraction form = onView(withId(R.id.email_register_form));
    }


    @Test
    public void correctDataInInputFields() {

        // Check all fields are correct

        onView(withId(R.id.first_name)).check(matches(withText("John")));
        onView(withId(R.id.last_name)).check(matches(withText("Doe")));
        onView(withId(R.id.email)).check(matches(withText("john.doe@example.com")));
    }

    @Test
    public void correctGender() {
        onView(withId(R.id.male_radio_button)).check(matches(isChecked()));
        onView(withId(R.id.female_radio_button)).check(matches(isNotChecked()));
    }

    @Test
    public void firstNameInputFieldIsAboveLastNameInputField() {
        onView(withId(R.id.first_name)).check(isAbove(withId(R.id.last_name)));
    }
}
