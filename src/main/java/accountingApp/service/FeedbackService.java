package accountingApp.service;

import accountingApp.entity.Feedback;
import accountingApp.repository.FeedbackRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    public List<Feedback> findAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public void addFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Feedback feedback) {
        feedbackRepository.delete(feedback);
    }

    public Feedback findFeedbackById(String id) {

        List<Feedback> feedbackList = feedbackRepository.findAll();

        for (Feedback feedback : feedbackList
        ) {
            if (feedback.getId().toString().equals(id)) {
                return feedback;
            }
        }

        return new Feedback(new ObjectId("111"),
                "Feedback not found", "Feedback not found",
                "Feedback not found");
    }

    public Feedback findFeedbackByName(String name) {
        return feedbackRepository.findByName(name);
    }

}
