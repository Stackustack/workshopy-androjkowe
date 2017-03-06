package qaworkshops.android.netguru.co.qaworshopsandroid.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.adapter.MainListAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String EMAIL_KEY = "email_key";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MainListAdapter mainListAdapter;

    public static void startActivity(Context context, String email) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EMAIL_KEY, email);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        ListItem item1 = new ListItem("ONE");
        ListItem item2 = new ListItem("TWO");
        ListItem item3 = new ListItem("THREE");

        mainListAdapter = new MainListAdapter(Arrays.asList(item1, item2, item3));
        recyclerView.setAdapter(mainListAdapter);
        recyclerView.setHasFixedSize(true);
    }
}
