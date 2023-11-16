package com.repo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.entity.FeedbackEntity;
import com.repo.FeedbackRepo;
import com.service.FeedbackServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FeedbackRepoTest {

    @Mock
    private FeedbackRepo feedbackRepo;

    @InjectMocks
    private FeedbackServiceImpl feedbackService; // Replace with your actual service class

    @Test
    public void testUpdateFeedback() {
        doNothing().when(feedbackRepo).updateFeedBack(anyBoolean());

        feedbackRepo.updateFeedBack(true);

        verify(feedbackRepo, times(1)).updateFeedBack(true);
    }

    @Test
    public void testFindByFeedbackAcknowledge() {
        List<FeedbackEntity> expectedFeedbackList = Arrays.asList(
                new FeedbackEntity(1, "Title1", "Sender1", "Body1", null, true),
                new FeedbackEntity(2, "Title2", "Sender2", "Body2", null, true)
        );

        when(feedbackRepo.findByfeedbackAcknowledge(true)).thenReturn(expectedFeedbackList);

        List<FeedbackEntity> actualFeedbackList = feedbackRepo.findByfeedbackAcknowledge(true);

        assertThat(actualFeedbackList).isEqualTo(expectedFeedbackList);
        verify(feedbackRepo, times(1)).findByfeedbackAcknowledge(true);
    }

    @Test
    public void testFindByFeedbackFrom() {
        List<FeedbackEntity> expectedFeedbackList = Arrays.asList(
                new FeedbackEntity(1, "Title1", "Sender1", "Body1", null, true),
                new FeedbackEntity(2, "Title2", "Sender1", "Body2", null, true)
        );

        when(feedbackRepo.findByfeedbackFrom("Sender1")).thenReturn(expectedFeedbackList);

        List<FeedbackEntity> actualFeedbackList = feedbackRepo.findByfeedbackFrom("Sender1");

        assertThat(actualFeedbackList).isEqualTo(expectedFeedbackList);
        verify(feedbackRepo, times(1)).findByfeedbackFrom("Sender1");
    }
}