package qaworkshops.android.netguru.co.qaworshopsandroid.data;

public class ListItem {

    private String title;
    private long id;

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
