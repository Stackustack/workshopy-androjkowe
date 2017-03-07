package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.addtolist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.App;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.shared.BaseMvpDialogFragment;

public class AddToListDialogFragment extends BaseMvpDialogFragment<AddToListContract.View,
        AddToListContract.Presenter> implements AddToListContract.View {

    @BindView(R.id.item_name_edit_text)
    EditText itemNameEditText;

    public static AddToListDialogFragment newInstance() {
        return new AddToListDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @NonNull
    @Override
    public AddToListContract.Presenter createPresenter() {
        return App.getAppComponent(getContext()).plusAddToListComponent().getPresenter();
    }

    @OnClick(R.id.add_button)
    public void onAddButtonClick() {
        getPresenter().validateItemName(itemNameEditText.getText().toString());
    }

    @Override
    public void passResultAndCloseFragment(ListItem listItem) {

    }

    @Override
    public void showItemNameRequired() {
        itemNameEditText.setError(getString(R.string.error_field_required));
        itemNameEditText.requestFocus();
    }
}
