package pojo;

public class Posts {

    private int id;
    private String title;
    private String views;

    // Default constructor - It is necessary to Deserialize the Objects. Also if we have more than 2 constructors the that constructors is called 'Telescopic Constructors'
    public Posts(){}

    public Posts(int id, String title, String views) {
        this.id = id;
        this.title = title;
        this.views = views;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getViews() {
        return views;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setViews(String views) {
        this.views = views;
    }
}
