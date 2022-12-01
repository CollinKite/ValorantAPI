package kite.collin.valorantapi.Model;

public class UserModel extends ModelAbstract{
    private String password;
    private String access;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public UserModel(String password, String access) {
        this.password = password;
        this.access = access;
    }

    public UserModel() {
    }
}
