package qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration;

import android.text.TextUtils;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

public class RegisterViewPresenter extends MvpNullObjectBasePresenter<RegisterViewContract.View>
        implements RegisterViewContract.Presenter {

    @Inject
    public RegisterViewPresenter() {
    }


    @Override
    public void checkFieldsCorrectness(String lastName, String password, String email) {
        if (TextUtils.isEmpty(lastName)) {
            getView().onEmptyLastNameError();
        } else if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            getView().onIncorrectEmailError();
        } else if (password.length() < 5) {
            getView().onPasswordToShortError();
        }
    }
}
