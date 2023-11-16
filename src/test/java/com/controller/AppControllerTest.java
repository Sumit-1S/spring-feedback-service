package com.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.controller.AppController;
import com.entity.FeedbackEntity;
import com.service.FeedbackService;

@ExtendWith(MockitoExtension.class)
public class AppControllerTest {

    @Mock
    private FeedbackService feedbackService;

    @InjectMocks
    private AppController appController;

    private MockMvc mockMvc;

    @Test
    public void testAddFeedback() throws Exception {
        doNothing().when(feedbackService).addFeedback(any(FeedbackEntity.class));

        mockMvc = MockMvcBuilders.standaloneSetup(appController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/feedbackService/addFeedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"feedbackTitle\":\"Title\",\"feedbackFrom\":\"Sender\",\"feedbackBody\":\"Body\",\"feedbackAcknowledge\":false}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(feedbackService, times(1)).addFeedback(any(FeedbackEntity.class));
    }

    @Test
    public void testGetAllUnacknowledgedFeedback() throws Exception {
        List<FeedbackEntity> feedbackList = Arrays.asList(
                new FeedbackEntity(1, "Title1", "Sender1", "Body1", new Date(System.currentTimeMillis()), false),
                new FeedbackEntity(2, "Title2", "Sender2", "Body2", new Date(System.currentTimeMillis()), false)
        );

        when(feedbackService.getAllUnacknowledgedFeeback()).thenReturn(feedbackList);

        mockMvc = MockMvcBuilders.standaloneSetup(appController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/feedbackService/getUnacknowledge"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));

        verify(feedbackService, times(1)).getAllUnacknowledgedFeeback();
    }

    @Test
    public void testGetAllAcknowledgedFeedback() throws Exception {
        List<FeedbackEntity> feedbackList = Arrays.asList(
                new FeedbackEntity(1, "Title1", "Sender1", "Body1", new Date(System.currentTimeMillis()), true),
                new FeedbackEntity(2, "Title2", "Sender2", "Body2", new Date(System.currentTimeMillis()), true)
        );

        when(feedbackService.getAllAcknowledgedFeeback()).thenReturn(feedbackList);

        mockMvc = MockMvcBuilders.standaloneSetup(appController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/feedbackService/getAcknowledge"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));

        verify(feedbackService, times(1)).getAllAcknowledgedFeeback();
    }

    @Test
    public void testFeedbackByOwner() throws Exception {
        List<FeedbackEntity> feedbackList = Arrays.asList(
                new FeedbackEntity(1, "Title1", "Sender1", "Body1", new Date(System.currentTimeMillis()), false),
                new FeedbackEntity(2, "Title2", "Sender1", "Body2", new Date(System.currentTimeMillis()), false)
        );

        when(feedbackService.feedbackByOwner("Sender1")).thenReturn(feedbackList);

        mockMvc = MockMvcBuilders.standaloneSetup(appController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/feedbackService/feedbackByOwner/{feedbackFrom}", "Sender1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));

        verify(feedbackService, times(1)).feedbackByOwner("Sender1");
    }

    @Test
    public void testUpdateFeedback() throws Exception {
        FeedbackEntity feedbackEntity = new FeedbackEntity(1, "Title", "Sender", "Body", new Date(System.currentTimeMillis()), true);

        when(feedbackService.UpdateFeedback(any(FeedbackEntity.class))).thenReturn("Updated");

        mockMvc = MockMvcBuilders.standaloneSetup(appController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/feedbackService/updateFeedback")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"feedbackAcknowledge\":true}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Updated"));

        verify(feedbackService, times(1)).UpdateFeedback(any(FeedbackEntity.class));
    }
}
