package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import javax.inject.Inject;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

public class MainViewPresenter extends MvpNullObjectBasePresenter<MainViewContract.View>
        implements MainViewContract.Presenter {


    @Inject
    public MainViewPresenter() {
    }

    @Override
    public void onAddItemToListAdded(ListItem listItem) {
        getView().addItemToList(listItem);
    }

    @Override
    public void onRemoveListItem(ListItem listItem) {
        getView().removeItem(listItem);
    }
}
