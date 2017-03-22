package qaworkshops.android.netguru.co.qaworshopsandroid.feature.registration;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.OnClick;
import qaworkshops.android.netguru.co.qaworshopsandroid.R;
import qaworkshops.android.netguru.co.qaworshopsandroid.app.App;

public class RegisterActivity extends MvpActivity<RegisterViewContract.View, RegisterViewContract.Presenter>
        implements RegisterViewContract.View {

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

    private RegisterViewComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initComponent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupSpinner();
    }

    @NonNull
    @Override
    public RegisterViewContract.Presenter createPresenter() {
        return component.getRegisterViewPresenter();
    }

    @OnClick(R.id.set_birthday_button)
    public void openDatePicker() {

    }

    @OnClick(R.id.email_sign_in_button)
    public void attemptRegister() {
        getPresenter().checkFieldsCorrectness(
                lastNameInputEditText.getEditableText().toString(),
                passwordInputEditText.getEditableText().toString(),
                passwordInputEditText.getEditableText().toString()
        );
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
