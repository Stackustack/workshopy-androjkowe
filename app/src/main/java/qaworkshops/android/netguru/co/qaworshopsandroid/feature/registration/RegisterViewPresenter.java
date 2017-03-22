package qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration;

import android.text.TextUtils;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

public class RegisterViewPresenter extends MvpNullObjectBasePresenter<RegisterViewContract.View>
        implements RegisterViewContract.Presenter {

    String lastName;
    String password;
    String email;
    String country;
    String gender;

    @Inject
    public RegisterViewPresenter() {
    }


    @Override
    public void checkFieldsCorrectness(String lastName, String password,
                                       String email, String country, String gender) {
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.country = country;
        this.gender = gender;

        checkCorrectness();
    }

    private void checkCorrectness() {
        if (TextUtils.isEmpty(lastName)) {
            getView().onEmptyLastNameError();
        } else if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            getView().onIncorrectEmailError();
        } else if (password.length() < 5) {
            getView().onPasswordToShortError();
        } else {
            registerUser();
        }
    }

    private void registerUser() {

    }
}
