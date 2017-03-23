package qaworkshops.android.netguru.co.qaworshopsandroid.feature.editprofile;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.text.DateFormat;

import javax.inject.Inject;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.User;

public class EditProfileViewPresenter extends MvpNullObjectBasePresenter<EditProfileViewContract.View>
        implements EditProfileViewContract.Presenter {

    private final Context context;
    private User user;

    @Inject
    public EditProfileViewPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void showUserData(User user) {
        if (user != null) {
            this.user = user;
            setUserData();
        } else {
            getView().onUserNullError();
        }
    }

    private void setUserData() {
        getView().setFirstName(user.getFirstName());
        getView().setLastName(user.getLastName());
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        getView().setBirthday(dateFormat.format(user.getBirthday()));
        getView().setEmail(user.getEmail());
        getView().setGender(user.getGender());
        getView().setCountry(user.getCountry());
    }
}
