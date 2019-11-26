package marketplace.repos;

import marketplace.entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepos extends CrudRepository<Image,Long> {
}
