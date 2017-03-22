package qaworkshops.android.netguru.co.qaworshopsandroid.feature.login;

import android.text.TextUtils;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

import qaworkshops.android.netguru.co.qaworshopsandroid.app.ActivityScope;

@ActivityScope
public class LoginPresenter extends MvpNullObjectBasePresenter<LoginContract.View>
        implements LoginContract.Presenter {

    private String email;

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void validateLoginData(String email, String password) {
        this.email = email;
        if (TextUtils.isEmpty(email)) {
            getView().showEmailRequired();
        } else if (TextUtils.isEmpty(password)) {
            getView().showPasswordRequired();
        } else {
            signInUSer();
        }
    }

    private void signInUSer() {
        getView().signInUser(email);
    }
}
