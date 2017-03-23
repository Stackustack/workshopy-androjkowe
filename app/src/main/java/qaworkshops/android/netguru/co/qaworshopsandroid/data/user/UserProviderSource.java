package qaworkshops.android.netguru.co.qaworshopsandroid.data.user;


public interface UserProviderSource {

    void createUser(User user);

    boolean doesUserExistInDb(String email);
}
