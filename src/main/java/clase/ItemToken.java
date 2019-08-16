package clase;

public class ItemToken {
    public String id;
    private String name;
    private String userId;
    private String siteId;
    private String categoryId;

    public ItemToken() {
    }

    public ItemToken( String name, String userId, String siteId, String categoryId) {
        this.name = name;
        this.userId = userId;
        this.siteId = siteId;
        this.categoryId = categoryId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}

