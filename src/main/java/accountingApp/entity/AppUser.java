package accountingApp.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "APPUSERS")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "userlogin")
    private String userName;

    @Column(name = "passwd")
    private String userPass;

    @Column(name = "active")
    private boolean isActive;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public AppUser(int id, String userName, String userPass, boolean isActive, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.userPass = userPass;
        this.isActive = isActive;
        this.roles = roles;
    }

    public AppUser(String userName, String userPass, boolean isActive, Set<Role> roles) {
        this.userName = userName;
        this.userPass = userPass;
        this.isActive = isActive;
        this.roles = roles;
    }

    public AppUser() {
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
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


    @Override
    public String toString() {
        return userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser)) return false;
        AppUser appUser = (AppUser) o;
        return getId() == appUser.getId() && isActive() == appUser.isActive()
                && Objects.equals(getUserName(), appUser.getUserName())
                && Objects.equals(getUserPass(), appUser.getUserPass())
                && Objects.equals(getRoles(), appUser.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getUserPass(), isActive(), getRoles());
    }
}
