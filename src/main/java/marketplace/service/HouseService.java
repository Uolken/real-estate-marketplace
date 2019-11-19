package marketplace.service;

import marketplace.entity.House;

import java.util.List;

public interface HouseService {
    public void saveHouse(House house);
    public List<House> getHouseByCoord(Double leftLat, Double leftLng, Double rightLat, Double rightLng);
}
