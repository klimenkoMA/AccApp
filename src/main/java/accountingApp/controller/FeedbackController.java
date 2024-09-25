package accountingApp.controller;

import accountingApp.entity.Feedback;
import accountingApp.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        String nameWithoutSpaces = name.trim();
        if (!nameWithoutSpaces.equals("") && !nameWithoutSpaces.equals(" ")) {
            Feedback feedback = new Feedback(name, email, message);
            feedbackService.addFeedback(feedback);
            List<Feedback> feedbackList = feedbackService.findAllFeedbacks();
            model.addAttribute("feedbackList", feedbackList);
        }
        return "main";
    }
}
