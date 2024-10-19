package accountingApp.controller;

import accountingApp.entity.Feedback;
import accountingApp.service.FeedbackService;
import org.bson.types.ObjectId;
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

    @Autowired
    FeedbackService feedbackService;

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

        try {
            String nameWithoutSpaces = name.trim();

            if (!nameWithoutSpaces.equals("") && !nameWithoutSpaces.equals(" ")) {
                Feedback feedback = new Feedback(name, email, message);
                feedbackService.addFeedback(feedback);
                List<Feedback> feedbackList = feedbackService.findAllFeedbacks();
                model.addAttribute("feedbackList", feedbackList);
            }
            return "main";
        } catch (Exception e) {
            return "main";
        }
    }

    @PostMapping("/deletefeedback")
    public String deleteFeedback(@RequestParam String id,
                                 Model model) {

        if (id.equals("") || id.equals(" ")) {
            return this.getFeedbacks(model);
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

            if (!idWithoutSpaces.equals("") && !idWithoutSpaces.equals(" ")) {
                feedbackService.deleteFeedback(feedback);
                feedbackList = feedbackService.findAllFeedbacks();
                model.addAttribute("feedbackList", feedbackList);
            }
            return this.getFeedbacks(model);
        } catch (Exception e) {
            return this.getFeedbacks(model);
        }
    }

    @PostMapping("/updatefeedback")
    public String updateFeedback(@RequestParam String id,
                                 @RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String message,
                                 Model model) {

        if (id.equals("") || id.equals(" ")) {
            return this.getFeedbacks(model);
        }
        try {
            String nameWithoutSpaces = name.trim();
            String emailWithoutSpaces = email.trim();
            String messageWithoutSpaces = message.trim();

            if (!nameWithoutSpaces.equals("") && !nameWithoutSpaces.equals(" ")
                    && !emailWithoutSpaces.equals("") && !emailWithoutSpaces.equals(" ")
                    && !messageWithoutSpaces.equals("") && !messageWithoutSpaces.equals(" ")
            ) {
                Feedback feedback = new Feedback(new ObjectId(id), nameWithoutSpaces
                        , emailWithoutSpaces, messageWithoutSpaces);
                feedbackService.addFeedback(feedback);
                List<Feedback> feedbackList = feedbackService.findAllFeedbacks();
                model.addAttribute("feedbackList", feedbackList);
            }
            return this.getFeedbacks(model);
        } catch (Exception e) {
            return this.getFeedbacks(model);
        }
    }

    @PostMapping("/findfeedbackbyid")
    public String findFeedback(@RequestParam String id,
                               Model model) {

        if (id.equals("") || id.equals(" ")) {
            return this.getFeedbacks(model);
        }
        try {
            Feedback feedback = feedbackService.findFeedbackById(id);

            List<Feedback> feedbackList = new ArrayList<>();
            feedbackList.add(feedback);
            model.addAttribute("feedbackList", feedbackList);
            return "feedbacks";
        } catch (Exception e) {
            return this.getFeedbacks(model);
        }
    }
}
