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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

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
            return getFeedbacks(model);
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

                ObjectId objectId = feedback.getId();
                long idCount = 1L;

                List<Feedback> feedbackList = feedbackService.findAllFeedbacks();

                Set<Long> idSet = feedbackList.stream()
                        .map(Feedback::getIdCount)
                        .collect(Collectors.toSet());

                idCount = LongStream.rangeClosed(1, idSet.size() + 1_000_000_000_000_000_000L)
                        .filter(idc -> !idSet.contains(idc))
                        .findFirst()
                        .orElse(0L);

                Map<ObjectId, Long> idLongMap = new HashMap<>();
                idLongMap.put(objectId, idCount);
                feedback.setIdMap(idLongMap);
                feedback.setIdCount(idCount);
                feedbackService.addFeedback(feedback);

                return getFeedbacks(model);
            }
            throw new Exception("Attribute is empty!");
        } catch (Exception e) {
            logger.error("*** FeedbackController.addNewFeedback(): wrong DB's values! *** "
                    + e.getMessage());
            return getFeedbacks(model);
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
            String realId = getIdFromMap(Long.parseLong(idWithoutSpaces));
            List<Feedback> feedbackList = feedbackService.findAllFeedbacks();

            Feedback feedback = feedbackList.stream()
                    .filter(fb -> fb.getId().toString().equals(realId))
                    .findFirst()
                    .orElse(null);

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
                String realId = getIdFromMap(Long.parseLong(idWithoutSpaces));
                assert realId != null;
                Feedback fbFromBD = feedbackService.findFeedbackById(realId);
                Feedback feedback = new Feedback(new ObjectId(realId)
                        , nameWithoutSpaces
                        , emailWithoutSpaces
                        , messageWithoutSpaces);
                feedback.setIdCount(fbFromBD.getIdCount());
                feedback.setIdMap(fbFromBD.getIdMap());

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
            String realId = getIdFromMap(Long.parseLong(id));
            Feedback feedback = feedbackService.findFeedbackById(realId);

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

    private String getIdFromMap(long id) {

        List<Feedback> feedbackList = feedbackService.findAllFeedbacks();
        if (feedbackList.isEmpty()) {
            logger.error("*** FeedbackController.getIdFromMap():" +
                    "  WRONG DB VALUES*** ");
            return null;
        }

        return feedbackList.stream()
                .flatMap(fb -> fb.getIdMap().entrySet().stream())
                .filter(entry -> entry.getValue() == id)
                .findFirst()
                .map(key -> key.getKey().toString())
                .orElse("");
    }

    @PostMapping("/findfeedbackbyattrs")
    public String findFeedbackByAttrs(@RequestParam String attrs,
                                      Model model) {

        if (checker.checkAttribute(attrs)
        ) {
            logger.warn("*** FeedbackController.findFeedbackByAttrs():  Attribute has a null value! ***");
            return getFeedbacks(model);
        }
        String attrsWithoutSpaces = attrs.trim().toLowerCase(Locale.ROOT);

        try {
            List<Feedback> feedbackList = new ArrayList<>();
            List<Feedback> feedbacks = feedbackService.findAllFeedbacks();

            for (Feedback fb : feedbacks
            ) {
                if ((fb.getIdCount() + "").contains(attrsWithoutSpaces)) {
                    feedbackList.add(fb);
                } else if (fb.getName().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    feedbackList.add(fb);
                } else if (fb.getEmail().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    feedbackList.add(fb);
                } else if (fb.getMessage().toLowerCase(Locale.ROOT)
                        .contains(attrsWithoutSpaces)) {
                    feedbackList.add(fb);
                }
            }
            model.addAttribute("feedbackList", feedbackList);
            if (feedbackList.isEmpty()) {
                logger.debug("*** FeedbackController.findFeedbackByAttrs():  DATA NOT FOUND IN DB***");
                return getFeedbacks(model);
            }
            return "feedbacks";
        } catch (Exception e) {
            logger.error("*** FeedbackController.findFeedbackByAttrs(): wrong DB's values! *** "
                    + e.getMessage());
            return getFeedbacks(model);
        }
    }
}
