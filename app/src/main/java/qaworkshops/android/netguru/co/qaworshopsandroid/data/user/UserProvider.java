package qaworkshops.android.netguru.co.qaworshopsandroid.data.user;

import javax.inject.Inject;

import io.realm.Realm;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

public class UserProvider implements UserProviderSource {

    private final Realm realm;

    @Inject
    public UserProvider(Realm realm) {
        this.realm = realm;
    }


    @Override
    public void createUser(User user) {
        realm.executeTransaction(db -> db.copyToRealm(user));
    }

    @Override
    public boolean doesUserExistInDb(String email) {
        return realm.where(User.class)
                .equalTo("email", email)
                .findFirst() != null;

    }

    @Override
    public void addItemToUserItemList(String email, ListItem listItem) {
        User user = getUserFromDb(email);
        realm.executeTransaction(db -> user.addItemToUserItemsList(listItem));
    }

    @Override
    public void removeItemFromUserItemList(String email, ListItem listItem) {
        User user = getUserFromDb(email);
        realm.executeTransaction(db -> user.removeItemFromUserItemsList(listItem));
        removeListItemFromDb(listItem);
    }

    private User getUserFromDb(String email) {
        return realm.where(User.class)
                .equalTo("email", email)
                .findFirst();
    }

    private void removeListItemFromDb(ListItem listItem) {
        realm.executeTransaction(db ->
                db.where(ListItem.class)
                        .equalTo("id", listItem.getId())
                        .findFirst()
                        .deleteFromRealm()
        );

    }
}
