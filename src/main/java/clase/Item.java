package clase;

public class Item {
    private String id;
    private String name;
    private User user;
    private Site site;

    public Item() {
    }

    public Item(String id, String name, User user, Site site) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", site=" + site +
                '}';
    }
}
