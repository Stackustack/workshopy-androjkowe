package qaworkshops.android.netguru.co.qaworshopsandroid.data.user;

import javax.inject.Inject;

import io.realm.Realm;

public class UserProvider implements UserProviderSource {

    private final Realm realm;

    @Inject
    public UserProvider(Realm realm) {
        this.realm = realm;
    }


    @Override
    public void createUser(User user) {
        realm.executeTransaction(db -> {
            db.copyToRealm(user);
        });

    }
}
