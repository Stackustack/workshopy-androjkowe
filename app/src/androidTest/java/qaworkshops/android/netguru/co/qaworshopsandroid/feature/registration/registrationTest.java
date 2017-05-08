package qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration;


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
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.login.LoginActivity;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class registrationTest {

    public static final String EMAIL_KEY = "email_key";

    private boolean initialised = false;

    @Rule
    public ActivityTestRule<LoginActivity> mMainActivityRule =
            new ActivityTestRule<>(
                    LoginActivity.class,
                    true,     // initialTouchMode
                    true);   // launchActivity. False to customize the intent


    @Test
    public void createNewUserWithValidData_Success() {
        onView(withId(R.id.email_register_button)).perform(click());
        onView(withId(R.id.first_name)).perform(replaceText("Janusz"));
        onView(withId(R.id.last_name)).perform(replaceText("Testowy"));
        onView(withId(R.id.email)).perform(replaceText("janusz.testowy@gmail.com"));
        onView(withId(R.id.password)).perform(replaceText("password"), closeSoftKeyboard());
        onView(withId(R.id.set_birthday_button)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.male_radio_button)).perform(click());
        onView(withId(R.id.sign_up_button)).perform(click());

        onView(withId(R.id.fab)).check(matches(isDisplayed()));
    }

    @Test
    public void createNewUserWithInvalidData_Failure() {
        onView(withId(R.id.email_register_button)).perform(click());
        onView(withId(R.id.first_name)).perform(replaceText("Janusz"));
        onView(withId(R.id.last_name)).perform(replaceText("Testowy"));
        onView(withId(R.id.email)).perform(replaceText("janusz.testowy@gmail.com"));
        onView(withId(R.id.password)).perform(replaceText("password"), closeSoftKeyboard());
        onView(withId(R.id.set_birthday_button)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.male_radio_button)).perform(click());
        onView(withId(R.id.sign_up_button)).perform(click());

        onView(withId(R.id.fab)).check(matches(isDisplayed()));
    }

}
