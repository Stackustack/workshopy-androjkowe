package qaworkshops.android.netguru.co.qaworshopsandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String EMAIL_KEY = "email_key";

    public static void startActivity(Context context, String email) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EMAIL_KEY, email);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
