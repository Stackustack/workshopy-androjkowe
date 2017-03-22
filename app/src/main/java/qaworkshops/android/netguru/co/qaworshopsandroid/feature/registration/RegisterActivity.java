package qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.App;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.main.addtolist.AddToListDialogFragment;

public class RegisterActivity extends MvpActivity<RegisterViewContract.View, RegisterViewContract.Presenter>
        implements RegisterViewContract.View, DatePickerFragment.DateSetListener {

    @BindView(R.id.first_name)
    TextInputEditText firstNameInputEditText;

    @BindView(R.id.last_name)
    TextInputEditText lastNameInputEditText;

    @BindView(R.id.email)
    TextInputEditText emailInputEditText;

    @BindView(R.id.password)
    TextInputEditText passwordInputEditText;

    @BindView(R.id.select_country_spinner)
    Spinner countrySpinner;

    @BindView(R.id.register_layout)
    LinearLayout registerLayout;

    private RegisterViewComponent component;
    private String country;
    private String gender;
    private Date birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initComponent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setupSpinner();
    }

    @NonNull
    @Override
    public RegisterViewContract.Presenter createPresenter() {
        return component.getRegisterViewPresenter();
    }

    @OnClick(R.id.set_birthday_button)
    public void openDatePicker() {
        DatePickerFragment
                .newInstance()
                .show(getFragmentManager(), AddToListDialogFragment.TAG);
    }

    @OnClick(R.id.email_sign_in_button)
    public void attemptRegister() {
        getPresenter().checkFieldsCorrectness(
                lastNameInputEditText.getEditableText().toString(),
                passwordInputEditText.getEditableText().toString(),
                emailInputEditText.getEditableText().toString(),
                country,
                gender,
                birthday
        );
    }

    @OnItemSelected(R.id.select_country_spinner)
    public void spinnerItemSelected(Spinner spinner, int position) {
        this.country = spinner.getItemAtPosition(position).toString();
    }

    @OnClick({R.id.male_radio_button, R.id.female_radio_button})
    public void onRadioButtonClicked(RadioButton radioButton) {
        boolean checked = radioButton.isChecked();
        if (checked) {
            gender = radioButton.getText().toString();
        }
    }

    @Override
    public void onEmptyLastNameError() {
        lastNameInputEditText.setError(getString(R.string.error_field_required));
        lastNameInputEditText.requestFocus();
    }

    @Override
    public void onPasswordToShortError() {
        passwordInputEditText.setError(getString(R.string.error_invalid_password));
        passwordInputEditText.requestFocus();
    }

    @Override
    public void onIncorrectEmailError() {
        emailInputEditText.setError(getString(R.string.error_invalid_email));
        emailInputEditText.requestFocus();
    }

    @Override
    public void onBirthdayRequiredError() {
        Snackbar.make(registerLayout, getString(R.string.set_birthday_error), Snackbar.LENGTH_LONG);
    }

    @Override
    public void onDateSet(Date date) {
        this.birthday = date;
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(adapter);
    }

    private void initComponent() {
        component = App.getAppComponent(this)
                .plusRegisterViewComponent();
        component.inject(this);
    }
}
