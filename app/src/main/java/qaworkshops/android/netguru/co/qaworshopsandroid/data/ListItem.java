package qaworkshops.android.netguru.co.qaworshopsandroid.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ListItem extends RealmObject {

    @PrimaryKey
    private long id;
    private String title;

    public ListItem(String title) {
        this.title = title;
        this.id = System.currentTimeMillis();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }
}
