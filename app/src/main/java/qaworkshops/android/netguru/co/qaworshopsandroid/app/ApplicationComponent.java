package qaworkshops.android.netguru.co.qaworshopsandroid.app;

import javax.inject.Singleton;

import dagger.Component;
import qaworkshops.android.netguru.co.qaworshopsandroid.feature.login.LoginComponent;

@Singleton
@Component(
        modules = {
                ApplicationModule.class
        })
public interface ApplicationComponent {

    LoginComponent plusLoginComponent();
}