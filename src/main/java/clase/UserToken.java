package clase;

public class UserToken {
    private String id;
    private String name;
    private String pass;
    private String token;

    public UserToken() {
    }

    public UserToken(String id, String name, String pass, String token) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
