package marketplace.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double Lat;
    private Double Lng;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;
    @Enumerated(EnumType.STRING)
    private HouseStatus status;
    private Integer price;

    public House() {
    }

    public House(Double lat, Double lng, User owner, HouseStatus status, Integer price) {
        Lat = lat;
        Lng = lng;
        this.owner = owner;
        this.status = status;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return Lat;
    }

    public void setLat(Double lat) {
        Lat = lat;
    }

    public Double getLng() {
        return Lng;
    }

    public void setLng(Double lng) {
        Lng = lng;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public HouseStatus getStatus() {
        return status;
    }

    public void setStatus(HouseStatus status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
