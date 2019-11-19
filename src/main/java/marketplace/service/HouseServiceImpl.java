package marketplace.service;

import marketplace.entity.House;
import marketplace.repos.HouseRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    HouseRepos houseRepos;

    @Override
    public void saveHouse(House house) {
        houseRepos.save(house);
    }

    @Override
    public List<House> getHouseByCoord(Double leftLat, Double leftLng, Double rightLat, Double rightLng) {
        List<House> houses = houseRepos.findByBetweenBounds(leftLat, leftLng, rightLat, rightLng);
        return houses;
    }

    @Autowired
    public HouseServiceImpl(HouseRepos houseRepos) {
        this.houseRepos = houseRepos;
    }
}
