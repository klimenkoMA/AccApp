package accountingApp.controller;

import accountingApp.entity.Feedback;
import accountingApp.service.FeedbackService;
import accountingApp.usefulmethods.Checker;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FeedbackController {

    final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private Checker checker;

    @GetMapping("/feedbacks")
    public String getFeedbacks(Model model) {
        List<Feedback> feedbackList = feedbackService.findAllFeedbacks();
        model.addAttribute("feedbackList", feedbackList);
        return "feedbacks";
    }

    @PostMapping("/addfeedback")
    public String addNewFeedback(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String message,
                                 Model model) {

        if (checker.checkAttribute(name)
                || checker.checkAttribute(email)
                || checker.checkAttribute(message)
        ) {
            logger.warn("*** FeedbackController.addNewFeedback():  Attribute has a null value! ***");
            return "main";
        }

        try {
            String nameWithoutSpaces = name.trim();
            String emailWithoutSpaces = email.trim();
            String messageWithoutSpaces = message.trim();

            if (!checker.checkAttribute(nameWithoutSpaces)
                    && !checker.checkAttribute(emailWithoutSpaces)
                    && !checker.checkAttribute(messageWithoutSpaces)
            ) {
                Feedback feedback = new Feedback(name, email, message);
                feedbackService.addFeedback(feedback);
                return "main";
            }
            throw new Exception("Attribute is empty!");
        } catch (Exception e) {
            logger.error("*** FeedbackController.addNewFeedback(): wrong DB's values! *** "
                    + e.getMessage());
            return "main";
        }
    }

    @PostMapping("/deletefeedback")
    public String deleteFeedback(@RequestParam String id,
                                 Model model) {

        if (checker.checkAttribute(id)
        ) {
            logger.warn("*** FeedbackController.deleteFeedback():  Attribute has a null value! ***");
            return getFeedbacks(model);
        }

        try {
            String idWithoutSpaces = id.trim();
            List<Feedback> feedbackList = feedbackService.findAllFeedbacks();
            Feedback feedback = new Feedback();

            for (Feedback feedback1 : feedbackList) {
                if (feedback1.getId().toString().equals(idWithoutSpaces)) {
                    feedback = feedback1;
                    break;
                }
            }

            feedbackService.deleteFeedback(feedback);

            return getFeedbacks(model);
        } catch (Exception e) {
            logger.error("*** FeedbackController.deleteFeedback(): wrong DB's values! *** "
                    + e.getMessage());
            return getFeedbacks(model);
        }
    }

    @PostMapping("/updatefeedback")
    public String updateFeedback(@RequestParam String id,
                                 @RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String message,
                                 Model model) {

        if (checker.checkAttribute(id)
                || checker.checkAttribute(name)
                || checker.checkAttribute(email)
                || checker.checkAttribute(message)) {
            logger.warn("*** FeedbackController.updateFeedback():  Attribute has a null value! ***");
            return getFeedbacks(model);
        }

        try {
            String idWithoutSpaces = id.trim();
            String nameWithoutSpaces = name.trim();
            String emailWithoutSpaces = email.trim();
            String messageWithoutSpaces = message.trim();

            if (!checker.checkAttribute(idWithoutSpaces)
                    && !checker.checkAttribute(nameWithoutSpaces)
                    && !checker.checkAttribute(emailWithoutSpaces)
                    && !checker.checkAttribute(messageWithoutSpaces)
            ) {
                Feedback feedback = new Feedback(new ObjectId(idWithoutSpaces), nameWithoutSpaces
                        , emailWithoutSpaces, messageWithoutSpaces);
                feedbackService.addFeedback(feedback);
                return getFeedbacks(model);
            }
            throw new Exception("Attribute is empty!");
        } catch (Exception e) {
            logger.error("*** FeedbackController.updateFeedback(): wrong DB's values! *** "
                    + e.getMessage());
            return getFeedbacks(model);
        }
    }

    @PostMapping("/findfeedbackbyid")
    public String findFeedback(@RequestParam String id,
                               Model model) {

        if (checker.checkAttribute(id)
        ) {
            logger.warn("*** FeedbackController.findFeedback():  Attribute has a null value! ***");
            return getFeedbacks(model);
        }

        try {
            Feedback feedback = feedbackService.findFeedbackById(id);

            List<Feedback> feedbackList = new ArrayList<>();
            feedbackList.add(feedback);
            model.addAttribute("feedbackList", feedbackList);
            return "feedbacks";
        } catch (Exception e) {
            logger.error("*** FeedbackController.findFeedback(): wrong DB's values! *** "
                    + e.getMessage());
            return getFeedbacks(model);
        }
    }
}
