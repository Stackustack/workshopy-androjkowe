package qaworkshops.android.netguru.co.qaworshopsandroid.data.user;

import javax.inject.Inject;

import io.realm.Realm;

import static qaworkshops.android.netguru.co.qaworshopsandroid.R.id.email;

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

    @Override
    public boolean doesUserExistInDb(String email) {
        return realm.where(User.class)
                .equalTo("email", email)
                .findFirst() != null;

    }
}
