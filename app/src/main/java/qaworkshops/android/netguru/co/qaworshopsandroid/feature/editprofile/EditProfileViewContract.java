package qaworkshops.android.netguru.co.qaworshopsandroid.feature.editprofile;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.User;

public interface EditProfileViewContract {

    interface View extends MvpView {

        void setFirstName(String firstName);

        void setLastName(String lastName);

        void setEmail(String email);

        void setBirthday(String birthday);

        void setCountry(String country);

        void setGender(String gender);

        void onUserNullError();

    }

    interface Presenter extends MvpPresenter<EditProfileViewContract.View> {

        void showUserData(User user);

    }
}
