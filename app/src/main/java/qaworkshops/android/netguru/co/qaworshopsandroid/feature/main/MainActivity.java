package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.App;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.login.LoginActivity;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.adapter.MainListAdapter;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.addtolist.AddToListDialogFragment;

public class MainActivity extends MvpActivity<MainViewContract.View, MainViewContract.Presenter>
        implements MainViewContract.View, AddToListDialogFragment.ItemAddedListener, RemoveItemListener,
        NavigationView.OnNavigationItemSelectedListener {

    public static final String EMAIL_KEY = "email_key";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private MainListAdapter mainListAdapter;
    private MainViewComponent component;

    public static void startActivity(Context context, String email) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EMAIL_KEY, email);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initComponent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        setSupportActionBar(toolbar);
        initializeDrawer();
        setupRecyclerView();

        getPresenter().onActivityStarted(getIntent().getStringExtra(EMAIL_KEY));
    }

    @NonNull
    @Override
    public MainViewContract.Presenter createPresenter() {
        return component.getMainViewPresenter();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_edit_profile) {

        } else if (id == R.id.nav_logout) {
            getPresenter().onLogoutAction();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemAdded(ListItem listItem) {
        getPresenter().onAddItemToListAdded(listItem);
    }

    @Override
    public void addItemToList(ListItem listItem) {
        mainListAdapter.addNewItem(listItem);
    }

    @Override
    public void removeItem(ListItem listItem) {
        mainListAdapter.removeItem(listItem);
    }

    @Override
    public void onUserItemListLoaded(List<ListItem> list) {
        mainListAdapter.addAllItemsToList(list);
    }

    @Override
    public void showLoginView() {
        LoginActivity.startActivity(this);
        finish();
    }

    @OnClick(R.id.fab)
    public void showDialogFragment() {
        AddToListDialogFragment
                .newInstance()
                .show(getSupportFragmentManager(), AddToListDialogFragment.TAG);
    }

    private void setupRecyclerView() {
        mainListAdapter = new MainListAdapter(this);
        recyclerView.setAdapter(mainListAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initializeDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initComponent() {
        component = App.getAppComponent(this)
                .plusMainViewComponent();
        component.inject(this);
    }

    @Override
    public void onListItemRemove(ListItem listItem) {
        getPresenter().onRemoveListItem(listItem);
    }
}
