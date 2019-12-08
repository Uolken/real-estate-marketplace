package marketplace.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double lat;
    private Double lng;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;


    @Enumerated(EnumType.STRING)
    private HouseStatus status;
    private Integer price;

//    @OneToMany(fetch = FetchType.EAGER)
    @ElementCollection
    private List<Long> pictures;


    private String country;
    private String city;
    private String street;
    private Integer buildingNumber;

    private Integer countOfRoom;
    private Integer countOfFloor;
    private Integer floor;
    private Integer area;

    private String description;




    public House() {
        this.pictures = new ArrayList<>();
    }

    public House(Double lat, Double lng, User owner, HouseStatus status, Integer price, List<Long> pictures, String country, String city, String street, Integer buildingNumber, Integer countOfRoom, Integer countOfFloor, Integer floor, Integer area, String description) {
        this.lat = lat;
        this.lng = lng;
        this.owner = owner;
        this.status = status;
        this.price = price;
        this.pictures = pictures;
        this.country = country;
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.countOfRoom = countOfRoom;
        this.countOfFloor = countOfFloor;
        this.floor = floor;
        this.area = area;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
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

    public List<Long> getPictures() {
        return pictures;
    }

    public void setPictures(List<Long> pictures) {
        this.pictures = pictures;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getCountOfRoom() {
        return countOfRoom;
    }

    public void setCountOfRoom(Integer countOfRoom) {
        this.countOfRoom = countOfRoom;
    }

    public Integer getCountOfFloor() {
        return countOfFloor;
    }

    public void setCountOfFloor(Integer countOfFloor) {
        this.countOfFloor = countOfFloor;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
