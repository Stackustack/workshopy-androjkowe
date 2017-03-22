package qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface RegisterViewContract {

    interface View extends MvpView {

        void onEmptyLastNameError();

        void onPasswordToShortError();

        void onIncorrectEmailError();

    }

    interface Presenter extends MvpPresenter<RegisterViewContract.View> {

        void checkFieldsCorrectness(String lastName, String password,
                                    String email, String country, String gender);
    }
}
