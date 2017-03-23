package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.UserProviderSource;

public class MainViewPresenter extends MvpNullObjectBasePresenter<MainViewContract.View>
        implements MainViewContract.Presenter {


    private final UserProviderSource userProviderSource;
    private String email;
    @Inject
    public MainViewPresenter(UserProviderSource userProviderSource) {
        this.userProviderSource = userProviderSource;
    }

    @Override
    public void onAddItemToListAdded(ListItem listItem) {
        userProviderSource.addItemToUserItemList(email, listItem);
        getView().addItemToList(listItem);
    }

    @Override
    public void onRemoveListItem(ListItem listItem) {
        userProviderSource.removeItemFromUserItemList(email, listItem);
        getView().removeItem(listItem);
    }

    @Override
    public void onActivityStarted(String email) {
        this.email = email;
    }
}
