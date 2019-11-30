package marketplace.repos;

import marketplace.entity.House;
import marketplace.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepos extends CrudRepository<House, Long> , JpaSpecificationExecutor {
    //List<House> findByPredicate(Specification<House> specification);
}
