package qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

public class RegisterViewPresenter extends MvpNullObjectBasePresenter<RegisterViewContract.View>
        implements RegisterViewContract.Presenter {

    @Inject
    public RegisterViewPresenter() {
    }
}
