package com.service;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.entity.FeedbackEntity;
//import com.entity.PolicyOwned;
//import com.entity.PolicyOwned;
import com.repo.FeedbackRepo;
import com.service.FeedbackServiceImpl;

@SpringBootTest(classes = FeedbackEntity.class)
@ExtendWith(MockitoExtension.class)
public class FeedbackServiceImplTest {

    @Mock
    private FeedbackRepo feedbackRepo;

    @InjectMocks
    private FeedbackServiceImpl service; // Replace with the actual name of your service implementation class
    private FeedbackEntity f1;
    private FeedbackEntity f2;
    
//    private Integer feedbackId;
//    private String feedbackTitle;
//    private String feedbackFrom;
//    private String feedbackBody;
//    private Date feedbackDate;
//    private Boolean feedbackAcknowledge;
    
    @BeforeEach
    public void beforeTest() {
        MockitoAnnotations.openMocks(this);
        f1=new FeedbackEntity();
        f1.setFeedbackId(1);
        f1.setFeedbackTitle("Feedback-101");
        f1.setFeedbackFrom("AppOwner");
        f1.setFeedbackBody("Keep up the good work");
        Date d1 = Date.valueOf("2023-11-01");
        f1.setFeedbackDate(d1);
        f1.setFeedbackAcknowledge(false);
        
        f2=new FeedbackEntity();
        f2.setFeedbackId(1);
        f2.setFeedbackTitle("Feedback-101");
        f2.setFeedbackFrom("AppOwner");
        f2.setFeedbackBody("Keep up the good work");
        Date d2 = Date.valueOf("2023-11-01");
        f2.setFeedbackDate(d1);
        f2.setFeedbackAcknowledge(true);
    }
    @Test
    public void testAddFeedback() {
        

        
        service.addFeedback(f1);

        // Then
        // Verify that the save method of feedbackRepo was called with the correct argument
        Mockito.verify(feedbackRepo).save(f1);
    }
    @Test
    public void testGetAllUnacknowledgedFeedback() {
        // Given
        List<FeedbackEntity> unacknowledgedFeedbackList = Arrays.asList(f1,f2);

        Mockito.when(feedbackRepo.findByfeedbackAcknowledge(false)).thenReturn(unacknowledgedFeedbackList);

        // When
        List<FeedbackEntity> result=service.getAllUnacknowledgedFeeback();

        // Then
        // Verify that the findByfeedbackAcknowledge method of feedbackRepo was called
        Mockito.verify(feedbackRepo).findByfeedbackAcknowledge(false);

        // Verify the result
        assertEquals(unacknowledgedFeedbackList, result);
    }
    
    
    @Test
    public void testGetAllAcknowledgedFeedback() {
        // Given
        List<FeedbackEntity> acknowledgedFeedbackList = Arrays.asList(f1,f2);

        Mockito.when(feedbackRepo.findByfeedbackAcknowledge(true)).thenReturn(acknowledgedFeedbackList);

        // When
        List<FeedbackEntity> result = service.getAllAcknowledgedFeeback();

        // Then
        // Verify that the findByfeedbackAcknowledge method of feedbackRepo was called
        Mockito.verify(feedbackRepo).findByfeedbackAcknowledge(true);

        // Verify the result
        assertEquals(acknowledgedFeedbackList, result);
    }
    @Test
    public void testFeedbackByOwner() {
        // Given
        String feedbackFrom = "AppOwner"; // Replace with the desired value

        List<FeedbackEntity> feedbackList = Arrays.asList(f1,f2);

        Mockito.when(feedbackRepo.findByfeedbackFrom(feedbackFrom)).thenReturn(feedbackList);

        // When
        List<FeedbackEntity> result = service.feedbackByOwner(feedbackFrom);

        // Then
        // Verify that the findByfeedbackFrom method of feedbackRepo was called
        Mockito.verify(feedbackRepo).findByfeedbackFrom(feedbackFrom);

        // Verify the result
        assertEquals(feedbackList, result);
    }
//    @Test
//    @Transactional // Ensure the test method is transactional
//    public void testUpdateFeedback() {
//        // Given
//        FeedbackEntity feedbackEntity = new FeedbackEntity();
//        feedbackEntity.setFeedbackAcknowledge(true); // Set the desired value
//
//        // When
//        String result = service.UpdateFeedback(feedbackEntity);
//
//        // Then
//        // Verify that the updateFeedback method of feedbackRepo was called with the correct argument
//        Mockito.verify(feedbackRepo).updateFeedBack(true);
//
//        // Verify the result
//        assertEquals("Updated", result);
//    }




    
}
