package accountingApp.controller;

import accountingApp.entity.Feedback;
import accountingApp.service.FeedbackService;
import accountingApp.usefulmethods.Checker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.*;

class FeedbackControllerTest {

    @InjectMocks
    private FeedbackController feedbackController;
    @Mock
    private FeedbackService feedbackService;
    @Mock
    private Checker checker;
    @Mock
    private Model model;

    private final List<Feedback> feedbackList;

    {
        Feedback f1 = new Feedback();
        Feedback f2 = new Feedback();
        Feedback f3 = new Feedback();

        feedbackList = Arrays.asList(f1, f2, f3);
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getFeedbacks() {

        Mockito.when(feedbackService.findAllFeedbacks()).thenReturn(feedbackList);

        String viewName = feedbackController.getFeedbacks(model);

        Assertions.assertEquals("feedbacks", viewName);

        verify(model).addAttribute("feedbackList", feedbackList);

        verify(feedbackService).findAllFeedbacks();
    }

    @Test
    void addFeedbackValid() {

        String name = "Name";
        String email = "name@name.ru";
        String message = "hello everyone bye everyone";

        String viewName = feedbackController.addNewFeedback(name, email, message, model);

        Assertions.assertEquals("feedbacks", viewName);

        verify(feedbackService, times(2)).addFeedback(any());
    }

    @Test
    void addFeedbackFail() {

        String name = " ";
        String email = " ";
        String message = "       ";

        String viewName = feedbackController.addNewFeedback(name, email, message, model);
        Feedback feedback = new Feedback(name, email, message);

        Assertions.assertEquals("feedbacks", viewName);

//        verify(model, never()).addAttribute("feedbackList", new ArrayList<Feedback>());

        verify(feedbackService, never()).addFeedback(feedback);
    }

    @Test
    void addFeedbackFailWithException() {

        String name = "Name";
        String email = "name@name.ru";
        String message = "hello everyone bye everyone";

        String viewName = feedbackController.addNewFeedback(name, email, message, model);

        Assertions.assertEquals("feedbacks", viewName);

        doThrow(new RuntimeException()).when(feedbackService).addFeedback(any());

        verify(feedbackService, never()).addFeedback(new Feedback());
    }

    @Test
    void deleteFeedbackValid() {

        String id = "Name";

        String viewName = feedbackController.deleteFeedback(id, model);

        Assertions.assertEquals("feedbacks", viewName);

        verify(model, times(1)).addAttribute("feedbackList", new ArrayList<Feedback>());

        verify(feedbackService, never()).deleteFeedback(any());
    }

    @Test
    void deleteFeedbackFail() {

        String id = " ";

        String viewName = feedbackController.deleteFeedback(id, model);

        Assertions.assertEquals("feedbacks", viewName);

        verify(model).addAttribute("feedbackList", new ArrayList<Feedback>());

        verify(feedbackService, never()).deleteFeedback(new Feedback(id, "dfdfd", " gfgfgg"));
    }

    @Test
    void deleteFeedbackFailWithException() {

        String id = "name";

        String viewName = feedbackController.deleteFeedback(id, model);

        doThrow(new RuntimeException()).when(feedbackService).deleteFeedback(new Feedback());

        Assertions.assertEquals("feedbacks", viewName);

        verify(model, times(1)).addAttribute("feedbackList", new ArrayList<Feedback>());

        verify(feedbackService, never()).deleteFeedback(new Feedback());
    }

    @Test
    void updateFeedbackValid() {

        String id = "1";
        String name = "Name";
        String email = "name@name.ru";
        String message = "hello everyone bye everyone";

//        String viewName = feedbackController.updateFeedback(id, name, email, message, model);

//        Assertions.assertEquals("feedbacks", feedbacks);

        verify(model, never()).addAttribute("feedbackList", new ArrayList<Feedback>());

    }

    @Test
    void updateFeedbackFail() {

        String id = " ";
        String name = " ";
        String email = "   ";
        String message = "       ";

        String viewName = feedbackController.updateFeedback(id, name, email, message, model);

        Assertions.assertEquals("feedbacks", viewName);

        verify(model).addAttribute("feedbackList", new ArrayList<Feedback>());

        verify(feedbackService, never()).addFeedback(new Feedback());
    }

    @Test
    void updateFeedbackFailWithException() {

        String id = "15";
        String name = "Name";
        String email = "name@name.ru";
        String message = "hello everyone bye everyone";

//        String viewName = feedbackController.updateFeedback(id, name, email, message, model);

        doThrow(new RuntimeException()).when(feedbackService).addFeedback(new Feedback());

//        Assertions.assertEquals("feedbacks", viewName);

//        verify(model).addAttribute("feedbackList", new ArrayList<Feedback>());

        verify(feedbackService, never()).addFeedback(new Feedback());
    }

    @Test
    void findFeedbackValid() {

        String id = "Name";

        String viewName = feedbackController.findFeedback(id, model);

        Assertions.assertEquals("feedbacks", viewName);

        verify(feedbackService, never()).findFeedbackById(any());
    }

    @Test
    void findFeedbackFail() {

        String id = " ";

        Feedback feedback = new Feedback(id, "ggfgf", "gfgfgfg");

        verify(feedbackService, never()).findFeedbackById(id);
    }

    @Test
    void findFeedbackFailWithException() {

        String id = "name";

        String viewName = feedbackController.findFeedback(id, model);

        doThrow(new RuntimeException()).when(feedbackService).findFeedbackById(id);

        Assertions.assertEquals("feedbacks", viewName);

        verify(feedbackService, never()).findFeedbackById(new Feedback().toString());
    }
}