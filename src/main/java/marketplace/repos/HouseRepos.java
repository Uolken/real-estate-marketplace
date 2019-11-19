package marketplace.repos;

import marketplace.entity.House;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepos extends CrudRepository<House, Long> {
    @Query(value="SELECT * FROM House WHERE ?1 < Lat AND Lat < ?3 AND ?2 < Lng AND Lng < ?4 ", nativeQuery = true)
    public List<House> findByBetweenBounds(Double leftLat, Double leftLng, Double rightLat, Double rightLng);
}
