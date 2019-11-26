package marketplace.service;

import marketplace.entity.House;

import java.util.List;

public interface HouseService {
    public void saveHouse(House house);
    public List<House> findHouseByCoord(Double leftLat, Double leftLng, Double rightLat, Double rightLng);
    public House findById(Long id) throws Exception;
    public List<House> findAll();
}
