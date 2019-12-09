package marketplace.service;

import marketplace.entity.House;
import marketplace.repos.HouseRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    private HouseRepos houseRepos;

    @Override
    public void saveHouse(House house) {
        houseRepos.save(house);
    }

    @Override
    public List<House> findMarkerByCoord(Double leftLat, Double leftLng, Double rightLat, Double rightLng) {

        return houseRepos.findAll((Specification<House>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.between(root.get("lat"), leftLat, rightLat),
                        criteriaBuilder.between(root.get("lng"), leftLng, rightLng)
                ));
    }

    @Override
    public House findById(Long id) throws Exception {
        return houseRepos.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public List<House> findAll() {
        List<House> houses =  (List<House>) houseRepos.findAll();
        return houses;
    }

    @Override
    public List<House> findAll(String userId) {
        Specification<House> specification = new Specification<House>() {
            @Override
            public Predicate toPredicate(Root<House> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (userId != null) {
                    predicates.add(criteriaBuilder.equal(root.get("owner").get("id"), userId));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

            }
        };
        return houseRepos.findAll(specification);
    }

    @Autowired
    public HouseServiceImpl(HouseRepos houseRepos) {
        this.houseRepos = houseRepos;
    }
}
