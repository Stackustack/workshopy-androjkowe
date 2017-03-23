package qaworkshops.android.netguru.co.qaworshopsandroid.feature.editprofile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.App;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.user.User;

import static qaworkshops.android.netguru.co.qaworshopsandroid.R.id.email;

public class EditProfileActivity extends MvpActivity<EditProfileViewContract.View, EditProfileViewContract.Presenter>
        implements EditProfileViewContract.View {

    public static final String USER_KEY = "user_key";

    @BindView(R.id.edit_profile_button)
    Button editProfileButton;

    @BindView(R.id.first_name)
    TextInputEditText firstNameInputEditText;

    @BindView(R.id.last_name)
    TextInputEditText lastNameInputEditText;

    @BindView(email)
    TextInputEditText emailInputEditText;

    @BindView(R.id.password)
    TextInputEditText passwordInputEditText;

    @BindView(R.id.birthday_text_view)
    TextView birthdayTextView;

    @BindView(R.id.select_country_spinner)
    Spinner countrySpinner;

    @BindView(R.id.female_radio_button)
    RadioButton femaleRadioButton;

    @BindView(R.id.male_radio_button)
    RadioButton maleRadioButton;

    private EditProfileViewComponent component;
    private ArrayAdapter<CharSequence> adapter;

    public static void startActivity(Context context, User user) {
        Intent intent = new Intent(context, EditProfileActivity.class);
        intent.putExtra(USER_KEY, user);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initComponent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        editProfileButton.setVisibility(View.VISIBLE);
        passwordInputEditText.setVisibility(View.GONE);

        setupSpinner();
        getPresenter().showUserData(getIntent().getParcelableExtra(USER_KEY));
    }

    @NonNull
    @Override
    public EditProfileViewContract.Presenter createPresenter() {
        return component.getEditProfileViewPresenter();
    }

    @Override
    public void setFirstName(String firstName) {
        firstNameInputEditText.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        lastNameInputEditText.setText(lastName);
    }

    @Override
    public void setEmail(String email) {
        emailInputEditText.setText(email);
    }

    @Override
    public void setBirthday(String birthday) {
        birthdayTextView.setText(birthday);
    }

    @Override
    public void setCountry(String country) {
        int spinnerPosition = adapter.getPosition(country);
        countrySpinner.setSelection(spinnerPosition);
    }

    @Override
    public void setGender(String gender) {
        if (gender != null) {
            if (gender.equals(getString(R.string.female))) {
                femaleRadioButton.setChecked(true);
                maleRadioButton.setChecked(false);
            } else if (gender.equals(getString(R.string.male))) {
                maleRadioButton.setChecked(true);
                femaleRadioButton.setChecked(false);
            }
        }
    }

    @Override
    public void onUserNullError() {
        Toast.makeText(this, getString(R.string.on_user_null_error), Toast.LENGTH_LONG).show();
    }

    private void setupSpinner() {
        adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);
    }

    private void initComponent() {
        component = App.getAppComponent(this)
                .plusEditProfileViewComponent();
        component.inject(this);
    }
}
