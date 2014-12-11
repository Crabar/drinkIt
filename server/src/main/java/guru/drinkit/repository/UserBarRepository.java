package guru.drinkit.repository;

import guru.drinkit.domain.UserBar;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by pkolmykov on 12/11/2014.
 */
public interface UserBarRepository extends MongoRepository<UserBar, ObjectId> {
}
