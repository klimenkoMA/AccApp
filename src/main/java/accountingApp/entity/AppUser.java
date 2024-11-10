package accountingApp.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="APPUSERS")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name="userlogin")
    private String userName;

    @Column(name="passwd")
    private String userPass;

    @Column(name="roles")
    private String roles;

    public AppUser(long id, String userName, String userPass, String roles) {
        this.id = id;
        this.userName = userName;
        this.userPass = userPass;
        this.roles = roles;
    }

    public AppUser(String userName, String userPass, String roles) {
        this.userName = userName;
        this.userPass = userPass;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;
        AppUser appUser = (AppUser) o;
        return getId() == appUser.getId() && getUserName().equals(appUser.getUserName()) && getUserPass().equals(appUser.getUserPass()) && getRoles().equals(appUser.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getUserPass(), getRoles());
    }
}
