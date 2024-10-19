package accountingApp.controller;

import accountingApp.entity.Feedback;
import accountingApp.service.FeedbackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

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
    void addNewFeedback() {
    }
}