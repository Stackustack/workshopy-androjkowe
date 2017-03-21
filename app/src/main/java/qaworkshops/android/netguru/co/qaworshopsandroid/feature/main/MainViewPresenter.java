package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

public class MainViewPresenter extends MvpNullObjectBasePresenter<MainViewContract.View>
        implements MainViewContract.Presenter {

    private ListItem listItem;
    private List<ListItem> itemsList;

    @Inject
    public MainViewPresenter() {
        itemsList = new ArrayList<>();
    }

    @Override
    public void onAddItemToListAdded(ListItem listItem) {
        this.listItem = listItem;
        itemsList.add(listItem);
        getView().addItemToList(listItem);
    }

    @Override
    public void onRemoveListItem(ListItem listItem) {

    }
}
