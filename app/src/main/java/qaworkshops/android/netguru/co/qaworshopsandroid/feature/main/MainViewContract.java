package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;


public interface MainViewContract {

    interface View extends MvpView {

        void addItemToList(ListItem listItem);

        void removeItem(ListItem listItem);

        void onUserItemListLoaded(List<ListItem> list);

    }

    interface Presenter extends MvpPresenter<MainViewContract.View> {

        void onAddItemToListAdded(ListItem listItem);

        void onRemoveListItem(ListItem listItem);

        void onActivityStarted(String email);

    }
}
