package accountingApp.repository;

import accountingApp.entity.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

    @Query("{'name' :  {$regex: ?0, $options: 'i' }}")
    Feedback findByName(String name);

    @Override
    List<Feedback> findAll();

}
