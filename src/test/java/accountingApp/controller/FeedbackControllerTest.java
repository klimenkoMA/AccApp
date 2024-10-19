package accountingApp.controller;

import accountingApp.entity.Feedback;
import accountingApp.service.FeedbackService;
import org.bson.types.ObjectId;
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
    Model model;

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

        Assertions.assertEquals("main", viewName);

        verify(model).addAttribute("feedbackList", new ArrayList<Feedback>());

        verify(feedbackService, times(1)).addFeedback(any());
    }

    @Test
    void addFeedbackFail() {

        String name = " ";
        String email = " ";
        String message = "       ";

        String viewName = feedbackController.addNewFeedback(name, email, message, model);

        Assertions.assertEquals("main", viewName);

        verify(model, never()).addAttribute("feedbackList", new ArrayList<Feedback>());

        verify(feedbackService, never()).addFeedback(any());
    }

    @Test
    void addFeedbackFailWithException() {

        String name = "Name";
        String email = "name@name.ru";
        String message = "hello everyone bye everyone";

        String viewName = feedbackController.addNewFeedback(name, email, message, model);

        Assertions.assertEquals("main", viewName);

        doThrow(new RuntimeException()).when(feedbackService).addFeedback(any());

        verify(feedbackService, never()).addFeedback(new Feedback());
    }

    @Test
    void deleteFeedbackValid() {

        String id = "Name";

        String viewName = feedbackController.deleteFeedback(id, model);

        Assertions.assertEquals("feedbacks", viewName);

        verify(model, times(2)).addAttribute("feedbackList", new ArrayList<Feedback>());

        verify(feedbackService, times(1)).deleteFeedback(any());
    }

    @Test
    void deleteFeedbackFail() {

        String id = " ";

        String viewName = feedbackController.deleteFeedback(id, model);

        Assertions.assertEquals("feedbacks", viewName);

        verify(model).addAttribute("feedbackList", new ArrayList<Feedback>());

        verify(feedbackService, never()).deleteFeedback(any());
    }

    @Test
    void deleteFeedbackFailWithException() {

        String id = "name";

        String viewName = feedbackController.deleteFeedback(id, model);

        doThrow(new RuntimeException()).when(feedbackService).deleteFeedback(new Feedback());

        Assertions.assertEquals("feedbacks", viewName);

        verify(model, times(2)).addAttribute("feedbackList", new ArrayList<Feedback>());

        verify(feedbackService, never()).deleteFeedback(new Feedback());
    }
}