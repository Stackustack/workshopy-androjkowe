package qaworkshops.android.netguru.co.qaworshopsandroid.data.user;


import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

public interface UserProviderSource {

    void createUser(User user);

    boolean doesUserExistInDb(String email);

    void addItemToUserItemList(String email, ListItem listItem);

    void removeItemFromUserItemList(String email, ListItem listItem);
}
