package qaworkshops.android.netguru.co.qaworshopsandroid.data.user;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import qaworkshops.android.netguru.co.qaworshopsandroid.data.ListItem;

public class User extends RealmObject {

    @PrimaryKey
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long birthday;
    private String country;
    private String gender;
    private RealmList<ListItem> userItemsList;

    public User() {
    }

    public User(String firstName, String lastName, String email,
                long birthday, String country, String gender) {
        this.id =  System.currentTimeMillis();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.country = country;
        this.gender = gender;
        this.userItemsList = new RealmList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public RealmList<ListItem> getUserItemsList() {
        return userItemsList;
    }

    public void addItemToUserItemsList(ListItem userItem) {
        if (userItemsList == null) {
            userItemsList = new RealmList<>();
        }
        userItemsList.add(userItem);
    }
}
