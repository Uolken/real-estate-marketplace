package marketplace.service;

import marketplace.entity.House;
import marketplace.entity.HouseStatus;

import java.util.List;

public interface HouseService {
    public void saveHouse(House house);
    public List<House> findMarkerByCoord(Double leftLat, Double leftLng, Double rightLat, Double rightLng);
    public House findById(Long id) throws Exception;
    public List<House> findAll();
    public List<House> findAll(String userId, HouseStatus[] houseTypes);
}
