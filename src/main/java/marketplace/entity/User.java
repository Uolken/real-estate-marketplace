package marketplace.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String avatar;
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private List<House> houses;

    public User() {
        this.houses = new ArrayList<>();
    }

    public User(String id, String name, String avatar, String email) {
        this();
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }
}
