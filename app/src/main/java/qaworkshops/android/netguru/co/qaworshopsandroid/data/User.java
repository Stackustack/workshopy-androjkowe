package qaworkshops.android.netguru.co.qaworshopsandroid.data;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {

    @PrimaryKey
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private long birthday;
    private String country;
    private String gender;
    private RealmList<ListItem> userItemsList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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